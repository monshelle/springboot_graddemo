package com.example.demo.actionitem;

public class ActionItemRequest {
    // 어떤 회의 요약본인지 식별
    private Long meetingSummaryId;

    // AI에게 전달할 회의 요약 텍스트
    private String summaryText;

    public Long getMeetingSummaryId() {
        return meetingSummaryId;
    }

    public void setMeetingSummaryId(Long meetingSummaryId) {
        this.meetingSummaryId = meetingSummaryId;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }
}
