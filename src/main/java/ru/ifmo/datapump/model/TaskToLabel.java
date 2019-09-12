package ru.ifmo.datapump.model;

import lombok.Data;

@Data
public class TaskToLabel {
    private Task task;
    private Label label;

    public TaskToLabel(Task task, Label label) {
        this.task = task;
        this.label = label;
    }
}
