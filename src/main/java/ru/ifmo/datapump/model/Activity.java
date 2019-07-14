package ru.ifmo.datapump.model;

import java.sql.Timestamp;

public class Activity {
    private Long id;
    private Timestamp createDate;
    private String activityType;//?
    private String oldValue;//?
    private String newValue;//?
    private TaskAuthor taskAuthor;
    private Task task;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setTaskAuthor(TaskAuthor taskAuthor) {
        this.taskAuthor = taskAuthor;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public TaskAuthor getTaskAuthor() {
        return taskAuthor;
    }

    public Task getTask() {
        return task;
    }
}
