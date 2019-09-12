package ru.ifmo.datapump.model;

import lombok.Data;

@Data
public class TaskAuthor {
    private Long id;
    private String name;
    private String email;
    private TaskManager taskManager;

    public TaskAuthor(Long id, String name, String mail, TaskManager taskManager) {
        this.id = id;
        this.name = name;
        this.email = mail;
        this.taskManager = taskManager;
    }
}
