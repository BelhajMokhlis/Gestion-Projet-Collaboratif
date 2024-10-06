package model.enums;

/**
 * Represents the status of a task in a task management system.
 */
public enum TaskStatus {
    /** Indicates that the task is yet to be started. */
    TO_DO,
    
    /** Indicates that the task is currently being worked on. */
    IN_PROGRESS,
    
    /** Indicates that the task has been finished. */
    COMPLETED;

    /**
     * Converts a string representation of a task status to its corresponding enum value.
     *
     * @param status The string representation of the task status.
     * @return The corresponding TaskStatus enum value.
     * @throws IllegalArgumentException if the input string does not match any enum constant.
     */
    public static TaskStatus fromString(String status) {
        return TaskStatus.valueOf(status.toUpperCase());
    }
}
