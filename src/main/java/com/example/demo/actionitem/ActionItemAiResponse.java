package com.example.demo.actionitem;

public class ActionItemAiResponse {

    private String task;
    private String assignee;
    private String dueDate; // "YYYY-MM-DD" 또는 null

    /* ===== Getter / Setter ===== */

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
