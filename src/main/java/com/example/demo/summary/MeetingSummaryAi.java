package com.example.demo.summary;

import com.example.demo.ai.OpenAiClient;
import com.example.demo.stt.SttSegment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingSummaryAi {

    private final OpenAiClient openAiClient;

    public MeetingSummaryAi(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String generateSummary(List<SttSegment> segments) {

        String conversation = segments.stream()
                .map(s -> "[" + s.getSpeaker() + "] " + s.getText())
                .reduce("", (a, b) -> a + "\n" + b);

        String prompt = """
        너는 대학생 개발팀의 회의록 정리 담당자다.
        다음은 회의 발화 기록이다.

        1. 논의 흐름을 유지하되,
        2. 결정 사항과 요청 사항을 중심으로,
        3. 회의 요약본 형태의 텍스트를 생성하라.

        형식:
        - 회의 주제 요약
        - 결정 사항
        - 요청 / 해야 할 일

        회의 발화 기록:
        """ + conversation;

        return openAiClient.call(prompt);
    }
}
