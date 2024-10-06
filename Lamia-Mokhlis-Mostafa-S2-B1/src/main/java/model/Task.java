package model;

import model.enums.TaskPriority;
import model.enums.TaskStatus;

import java.time.LocalDate;

/**
 * Represents a task in the project management system.
 */
public class Task {
    private int taskID;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private Project project;
    private Membre member;

    /**
     * Default constructor for Task.
     */
    public Task() {
    }

    /**
     * Constructs a Task with all parameters including member.
     *
     * @param title        The title of the task
     * @param description  The description of the task
     * @param priority     The priority of the task
     * @param status       The status of the task
     * @param creationDate The creation date of the task
     * @param dueDate      The due date of the task
     * @param project      The project associated with the task
     * @param member       The member assigned to the task
     */
    public Task(String title, String description, TaskPriority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate, Project project, Membre member) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
        this.member = member;
    }

    /**
     * Constructs a Task with all parameters except member.
     *
     * @param title        The title of the task
     * @param description  The description of the task
     * @param priority     The priority of the task
     * @param status       The status of the task
     * @param creationDate The creation date of the task
     * @param dueDate      The due date of the task
     * @param project      The project associated with the task
     */
    public Task(String title, String description, TaskPriority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate, Project project) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
    }

    /**
     * Gets the task ID.
     *
     * @return The task ID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Sets the task ID.
     *
     * @param taskID The task ID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The title of the task to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the priority of the task.
     *
     * @return The priority of the task
     */
    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority of the task to set
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The status of the task to set
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Gets the creation date of the task.
     *
     * @return The creation date of the task
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the task.
     *
     * @param creationDate The creation date of the task to set
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the due date of the task.
     *
     * @return The due date of the task
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate The due date of the task to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the project associated with the task.
     *
     * @return The project associated with the task
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project associated with the task.
     *
     * @param project The project associated with the task to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Gets the member assigned to the task.
     *
     * @return The member assigned to the task
     */
    public Membre getMember() {
        return member;
    }

    /**
     * Sets the member assigned to the task.
     *
     * @param member The member assigned to the task to set
     */
    public void setMember(Membre membre) {
        this.member = membre;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", dueDate=" + dueDate +
                '}';
    }
}

