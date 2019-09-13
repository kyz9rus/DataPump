package ru.ifmo.datapump.model;

import lombok.Data;

@Data
public class TaskAuthor {
    private long id;
    private String name;
    private String email;
    private TaskManager taskManager;

    public TaskAuthor(long id, String name, String mail, TaskManager taskManager) {
        this.id = id;
        this.name = name;
        this.email = mail;
        this.taskManager = taskManager;
    }
}
