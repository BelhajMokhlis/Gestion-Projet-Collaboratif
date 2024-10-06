package model;

import model.enums.TaskPriority;
import model.enums.TaskStatus;

import java.time.LocalDate;

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

    public Task() {
    }

    public Task( String title, String description, TaskPriority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate, Project project, Membre member) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
        this.member = member;
    }
    public Task(String title, String description, TaskPriority priority, TaskStatus status, LocalDate creationDate, LocalDate dueDate,Project project) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Membre getMember() {
        return member;
    }

    public void setMember(Membre membre) {
        this.member = membre;
    }

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

