package com.example.demo.actionitem;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "action_item")
public class ActionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 회의 요약본에서 생성되었는지
    private Long meetingSummaryId;

    // 실행해야 할 작업 내용
    @Column(nullable = false)
    private String task;

    // 담당자 (없을 수 있음)
    private String assignee;

    // 마감 기한 (없을 수 있음)
    private LocalDate dueDate; // null 가능

    // AI가 생성했는지 여부 (수동 추가 대비)
    private boolean generatedByAi = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetingSummaryId() {
        return meetingSummaryId;
    }

    public void setMeetingSummaryId(Long meetingSummaryId) {
        this.meetingSummaryId = meetingSummaryId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isGeneratedByAi() {
        return generatedByAi;
    }

    public void setGeneratedByAi(boolean generatedByAi) {
        this.generatedByAi = generatedByAi;
    }
}
