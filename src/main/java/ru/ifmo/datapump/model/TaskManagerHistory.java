package ru.ifmo.datapump.model;

import java.sql.Timestamp;

public class TaskManagerHistory {
    private long id;
    private Timestamp lastSyncDate;
    private String status;//?
    private String reason;//?
    private TaskManager taskManager;
}
