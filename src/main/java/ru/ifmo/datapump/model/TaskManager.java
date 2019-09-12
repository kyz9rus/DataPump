package ru.ifmo.datapump.model;

import lombok.Data;
import ru.ifmo.datapump.model.enums.TaskManagerType;

@Data
public class TaskManager {
    private Long id;
    private String name;
    private String description;
    private TaskManagerType taskManagerType;
    private String projectId; // -> node_id


    public TaskManager(Long id, String name, String description, TaskManagerType taskManagerType, String projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskManagerType = taskManagerType;
        this.projectId = projectId;
    }
}
