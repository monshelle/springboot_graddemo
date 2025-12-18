package com.example.demo.summary;

public class SummaryResponse {

    private Long meetingId;
    private String summaryText;

    public SummaryResponse(Long meetingId, String summaryText) {
        this.meetingId = meetingId;
        this.summaryText = summaryText;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public String getSummaryText() {
        return summaryText;
    }
}
