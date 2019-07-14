package ru.ifmo.datapump.model;

public class Label {
    private int id;
    private String name;
    private Task task;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Task getTask() {
        return task;
    }
}
