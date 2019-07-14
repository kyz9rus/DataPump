package ru.ifmo.datapump.model;

public class TaskAuthor {
    private Long id;
    private String name;
    private String mail;
    private TaskManager taskManager;

    public TaskAuthor(String name, String mail, TaskManager taskManager) {
        this.name = name;
        this.mail = mail;
        this.taskManager = taskManager;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }
}
