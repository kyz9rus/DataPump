package ru.ifmo.datapump.model;

import lombok.Data;

@Data
public class Label {
    private long id;
    private String name;

    public Label(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
