package service;

import model.Task;
import repository.Interface.TaskRepository;
import repository.impl.TaskRepositoryImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing tasks.
 * This class provides methods for creating, retrieving, updating, and deleting tasks,
 * as well as various query operations on tasks.
 */
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Constructs a new TaskService with a TaskRepositoryImpl.
     */
    public TaskService() {
        this.taskRepository = new TaskRepositoryImpl();
    }

    /**
     * Creates a new task.
     *
     * @param task The task to be created
     * @throws IllegalArgumentException if the task input is invalid
     */
    public void createTask(Task task) throws IllegalArgumentException {
        validateTaskInput(task);
        taskRepository.create(task);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param taskID The ID of the task to retrieve
     * @return The task with the specified ID
     */
    public Task getTask(int taskID) {
        return taskRepository.read(taskID);
    }

    /**
     * Retrieves all tasks.
     *
     * @return A list of all tasks
     */
    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    /**
     * Updates an existing task.
     *
     * @param task The task to be updated
     */
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param taskID The ID of the task to delete
     */
    public void deleteTask(int taskID) {
        taskRepository.delete(taskID);
    }

    /**
     * Retrieves all tasks for a specific project.
     *
     * @param projectID The ID of the project
     * @return A list of tasks associated with the specified project
     */
    public List<Task> getProjectTasks(int projectID){
        return taskRepository.getAllProjectTasks(projectID).orElse(Collections.emptyList());
    }

    /**
     * Retrieves all tasks assigned to a specific member.
     *
     * @param memberId The ID of the member
     * @return A list of tasks assigned to the specified member
     */
    public List<Task> getTaskByMemberId(int memberId){
    	List<Task> tasks = taskRepository.getAll();
    	return tasks.stream().filter(task->task.getMember().getId() == memberId).collect(Collectors.toList());
    }

    /**
     * Retrieves a paginated list of tasks for a specific project.
     *
     * @param projectID The ID of the project
     * @param page The page number
     * @param size The number of tasks per page
     * @return A paginated list of tasks for the specified project
     */
    public List<Task> getPaginatedProjectTasks(int projectID, int page, int size) {
        return taskRepository.getPaginatedProjectTasks(projectID, page, size).orElse(Collections.emptyList());
    }

    /**
     * Counts the total number of tasks for a specific project.
     *
     * @param projectID The ID of the project
     * @return The total number of tasks for the specified project
     */
    public int getTotalTasksForProject(int projectID) {
        return (int) taskRepository.getAll().stream()
                .filter(task -> task.getProject().getId() == projectID)
                .count();
    }

    /**
     * Validates the input for a task.
     *
     * @param task The task to validate
     * @throws IllegalArgumentException if any of the task fields are invalid
     */
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
