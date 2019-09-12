package ru.ifmo.datapump.model.enums;

public enum TaskStatus {
    OPEN, CLOSED, ALL;

    public static TaskStatus getStatus(String status) {
        switch (status) {
            case "open":
                return OPEN;
            case "closed":
                return CLOSED;
            case "all":
                return ALL;
        }
        return OPEN;
    }
}
