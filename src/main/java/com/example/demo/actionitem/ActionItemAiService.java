package com.example.demo.actionitem;

import com.example.demo.ai.OpenAiClient;
import org.springframework.stereotype.Service;

@Service
public class ActionItemAiService {
    private final OpenAiClient openAiClient;
    private final ActionItemPromptTemplate promptTemplate;

    public ActionItemAiService(OpenAiClient openAiClient,
                               ActionItemPromptTemplate promptTemplate) {
        this.openAiClient = openAiClient;
        this.promptTemplate = promptTemplate;
    }

    public String extractActionItems(String summaryText) {
        String prompt = promptTemplate.build(summaryText);
        return openAiClient.chat(prompt);
    }
}
