package service;

import model.Task;
import repository.Interface.TaskRepository;
import repository.impl.TaskRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepositoryImpl();
    }

    public void createTask(Task task) throws IllegalArgumentException {
        validateTaskInput(task);
        taskRepository.create(task);
    }

    public Task getTask(int taskID) {
        return taskRepository.read(taskID);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    public void deleteTask(int taskID) {
        taskRepository.delete(taskID);
    }

    public List<Task> getProjectTasks(int projectID){
        if (taskRepository.getAllProjectTasks(projectID).isPresent()){
            return taskRepository.getAllProjectTasks(projectID).get();
        }else {
            return null;
        }
    }

    private void validateTaskInput(Task task) {
        // Validate title
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        // Validate description
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        // Validate priority
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Priority must be selected.");
        }

        // Validate due date
        if (task.getDueDate() == null) {
            throw new IllegalArgumentException("Due date cannot be null.");
        }

        // Check if due date is
        if (task.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date must be in the future.");
        }
    }
}
