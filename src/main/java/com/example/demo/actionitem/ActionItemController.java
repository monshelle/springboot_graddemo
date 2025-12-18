package com.example.demo.actionitem;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action-items")
public class ActionItemController {
    private final ActionItemAiService aiService;
    private final ActionItemParser parser;
    private final ActionItemService actionItemService;

    public ActionItemController(ActionItemAiService aiService,
                                ActionItemParser parser,
                                ActionItemService actionItemService) {
        this.aiService = aiService;
        this.parser = parser;
        this.actionItemService = actionItemService;
    }

    @PostMapping
    public List<ActionItem> generateActionItems(
            @RequestBody ActionItemRequest request) {

        // 1. AI 호출
        String aiJson = aiService.extractActionItems(request.getSummaryText());

        // 2. 파싱
        List<ActionItem> items =
                parser.parse(aiJson, request.getMeetingSummaryId());

        // 3. 저장
        return actionItemService.saveAll(items);
    }
}
