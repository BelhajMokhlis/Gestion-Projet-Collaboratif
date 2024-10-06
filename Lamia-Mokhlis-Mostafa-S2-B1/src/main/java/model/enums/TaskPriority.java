package model.enums;


/**
 * Represents the priority levels for tasks.
 */
public enum TaskPriority {
    /** Low priority task. */
    LOW,
    /** Medium priority task. */
    MEDIUM,
    /** High priority task. */
    HIGH;

    /**
     * Converts a string representation of priority to a TaskPriority enum value.
     *
     * @param priority The string representation of the priority.
     * @return The corresponding TaskPriority enum value.
     * @throws IllegalArgumentException if the input string doesn't match any TaskPriority value.
     */
    public static TaskPriority fromString(String priority) {
        return TaskPriority.valueOf(priority.toUpperCase());
    }
}

