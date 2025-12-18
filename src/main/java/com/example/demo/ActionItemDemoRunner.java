package com.example.demo;

import com.example.demo.actionitem.*;
import com.example.demo.stt.*;
import com.example.demo.summary.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ActionItemDemoRunner implements CommandLineRunner {

    private final SttJsonLoader sttLoader;   // ✅ 여기
    private final MeetingSummaryAi summaryAi;
    private final ActionItemAiService actionItemAi;
    private final ActionItemParser parser;
    private final ActionItemService actionItemService;

    public ActionItemDemoRunner(
            SttJsonLoader sttLoader,
            MeetingSummaryAi summaryAi,
            ActionItemAiService actionItemAi,
            ActionItemParser parser,
            ActionItemService actionItemService
    ) {
        this.sttLoader = sttLoader;
        this.summaryAi = summaryAi;
        this.actionItemAi = actionItemAi;
        this.parser = parser;
        this.actionItemService = actionItemService;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1️⃣ STT JSON 로드
        List<SttSegment> segments = sttLoader.loadSegments();

        // 2️⃣ 요약 생성
        String summary = summaryAi.generateSummary(segments);

        // 3️⃣ 액션 아이템 추출
        String aiJson = actionItemAi.extractActionItems(summary);

        // 4️⃣ JSON → ActionItem
        List<ActionItem> items = parser.parse(aiJson, 1L);

        // 5️⃣ In-Memory 저장
        List<ActionItem> saved = actionItemService.saveAll(items);

        // 6️⃣ 파일로 저장
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        Files.writeString(
                Path.of("action_items.json"),
                om.writerWithDefaultPrettyPrinter().writeValueAsString(saved)
        );

        System.out.println("✅ action_items.json 생성 완료");
    }
}
