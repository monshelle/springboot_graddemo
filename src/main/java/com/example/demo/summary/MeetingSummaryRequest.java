package com.example.demo.summary;

import com.example.demo.stt.SttSegment;

import java.util.List;


public class MeetingSummaryRequest {

    private Long meetingId;
    private List<SttSegment> segments;

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public List<SttSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<SttSegment> segments) {
        this.segments = segments;
    }
}
