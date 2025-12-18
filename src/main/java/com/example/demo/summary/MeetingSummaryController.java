package com.example.demo.summary;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingSummaryController {

    private final MeetingSummaryAi summaryAiService;

    public MeetingSummaryController(MeetingSummaryAi summaryAiService) {
        this.summaryAiService = summaryAiService;
    }

    @PostMapping("/{meetingId}/summary")
    public SummaryResponse createSummary(
            @PathVariable Long meetingId,
            @RequestBody MeetingSummaryRequest request) {

        String summaryText =
                summaryAiService.generateSummary(request.getSegments());

        return new SummaryResponse(meetingId, summaryText);
    }
}
