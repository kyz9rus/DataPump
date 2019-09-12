package ru.ifmo.datapump.model;

import lombok.Data;

@Data
public class Label {
    private Long id;
    private String name;

    public Label(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
