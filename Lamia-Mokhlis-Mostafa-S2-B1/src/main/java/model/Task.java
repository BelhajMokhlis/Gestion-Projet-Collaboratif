package model;

import model.enums.TaskPriority;
import model.enums.TaskStatus;

import java.util.Date;

public class Task {
    private int taskID;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private Date creationDate;
    private Date dueDate;

    public Task() {
    }

    public Task(int taskID, String title, String description, TaskPriority priority, TaskStatus status, Date creationDate, Date dueDate) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

