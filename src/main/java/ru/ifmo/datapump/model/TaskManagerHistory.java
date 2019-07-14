package ru.ifmo.datapump.model;

import java.sql.Timestamp;

public class TaskManagerHistory {
    private Long id;
    private Timestamp lastSyncDate;
    private String status;//?
    private String reason;//?
    private TaskManager taskManager;
}
