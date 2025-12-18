package com.example.demo.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenAiClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OpenAiClient(
            @Value("${openai.api.key}") String apiKey
    ) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /** 공통 진입점 */
    public String call(String prompt) {
        return chat(prompt);
    }

    /**
     * 회의 요약 / 액션 아이템 공용 GPT 호출
     * @return GPT가 생성한 content 문자열만 반환
     */
    public String chat(String prompt) {

        String requestBody = """
        {
          "model": "gpt-4.1-mini",
          "messages": [
            { "role": "user", "content": "%s" }
          ]
        }
        """.formatted(escape(prompt));

        String rawResponse = restClient.post()
                .uri("/chat/completions")
                .body(requestBody)
                .retrieve()
                .body(String.class);

        return extractContent(rawResponse);
    }

    /**
     * OpenAI 응답 JSON에서 choices[0].message.content만 추출
     */
    private String extractContent(String responseJson) {
        try {
            JsonNode root = objectMapper.readTree(responseJson);
            return root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "OpenAI 응답 파싱 실패\n" + responseJson, e);
        }
    }

    // 프롬프트 JSON 깨짐 방지
    private String escape(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}
