package com.example.demo.summary;

import jakarta.persistence.*;

@Entity
@Table(name = "meeting_summary")
public class MeetingSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long meetingId;

    @Lob
    @Column(nullable = false)
    private String summaryText;

    protected MeetingSummary() {
    }

    public MeetingSummary(Long meetingId, String summaryText) {
        this.meetingId = meetingId;
        this.summaryText = summaryText;
    }

    public Long getId() {
        return id;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public String getSummaryText() {
        return summaryText;
    }
}
