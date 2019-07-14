package ru.ifmo.datapump.model;

import ru.ifmo.datapump.model.enums.TaskStatus;

import java.sql.Timestamp;

public class Task {
    private Long id;
    private String name;
    private String description;
    private Timestamp createDate;
    private TaskStatus status;
    private Timestamp deadlineDate;
    private int priority;
    private int severity;
//    private Long handlerId;//?
    private TaskAuthor taskAuthor;
    private TaskManager taskManager;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setDeadlineDate(Timestamp deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setTaskAuthor(TaskAuthor taskAuthor) {
        this.taskAuthor = taskAuthor;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Timestamp getDeadlineDate() {
        return deadlineDate;
    }

    public int getPriority() {
        return priority;
    }

    public int getSeverity() {
        return severity;
    }

    public TaskAuthor getTaskAuthor() {
        return taskAuthor;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
