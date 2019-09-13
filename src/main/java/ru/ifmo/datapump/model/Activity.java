package ru.ifmo.datapump.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Activity {
    private long id;
    private Timestamp createDate;
    private String activityType;//?
    private String oldValue;//?
    private String newValue;//?
    private TaskAuthor taskAuthor;
    private Task task;
}
