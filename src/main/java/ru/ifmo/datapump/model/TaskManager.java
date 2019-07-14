package ru.ifmo.datapump.model;

public class TaskManager {
    private Long id;
    private String name;
    private String description;
    private String TaskManagerType;//?
//    private int projectId;


    public TaskManager(String name, String description, String taskManagerType) {
        this.name = name;
        this.description = description;
        TaskManagerType = taskManagerType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskManagerType(String taskManagerType) {
        TaskManagerType = taskManagerType;
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

    public String getTaskManagerType() {
        return TaskManagerType;
    }
}
