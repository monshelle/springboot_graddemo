package com.example.demo.actionitem;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ActionItemParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ActionItem> parse(String json, Long meetingSummaryId) {
        try {
            List<ActionItemAiResponse> responses =
                    objectMapper.readValue(json,
                            new TypeReference<List<ActionItemAiResponse>>() {});

            return responses.stream()
                    .map(r -> {
                        ActionItem item = new ActionItem();
                        item.setMeetingSummaryId(meetingSummaryId);
                        item.setTask(r.getTask());
                        item.setAssignee(r.getAssignee());
                        if (r.getDueDate() != null) {
                            item.setDueDate(LocalDate.parse(r.getDueDate()));
                        }
                        return item;
                    })
                    .toList();

        } catch (Exception e) {
            throw new IllegalStateException("AI ActionItem JSON 파싱 실패", e);
        }
    }
}
