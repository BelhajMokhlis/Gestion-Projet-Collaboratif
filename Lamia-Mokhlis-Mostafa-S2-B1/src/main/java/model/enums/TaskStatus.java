package model.enums;

public enum TaskStatus {
    TO_DO,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String status) {
        return TaskStatus.valueOf(status.toUpperCase());
    }
}
