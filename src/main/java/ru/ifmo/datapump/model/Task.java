package ru.ifmo.datapump.model;

import lombok.Data;
import ru.ifmo.datapump.model.enums.TaskStatus;

import java.sql.Date;
import java.util.List;

@Data
public class Task {
    private Long id;
    private String name;
    private String description;
    private Date createDate;
    private Date deadlineDate;
    private TaskStatus status;
    private Integer priority;
    private Integer severity;
    //    private Long handlerId; // на кого захандлена
    private TaskAuthor taskAuthor;
    private TaskManager taskManager;
    private List<Label> labels;

    public Task(Long id, String name, String description, Date createDate, Date deadlineDate, TaskStatus status,
                Integer priority, Integer severity, TaskAuthor taskAuthor, TaskManager taskManager, List<Label> labels) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deadlineDate = deadlineDate;
        this.status = status;
        this.priority = priority;
        this.severity = severity;
        this.taskAuthor = taskAuthor;
        this.taskManager = taskManager;
        this.labels = labels;
    }
}
