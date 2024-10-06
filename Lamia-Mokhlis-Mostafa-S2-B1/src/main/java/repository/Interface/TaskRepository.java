package repository.Interface;

import model.Task;

import java.util.List;
import java.util.Optional;

/**
 * Interface for managing Task entities in the repository.
 */
public interface TaskRepository {
    /**
     * Creates a new task in the repository.
     *
     * @param task The task to be created.
     */
    void create(Task task);

    /**
     * Retrieves a task by its ID.
     *
     * @param taskID The ID of the task to retrieve.
     * @return The task with the specified ID.
     */
    Task read(int taskID);

    /**
     * Retrieves all tasks from the repository.
     *
     * @return A list of all tasks.
     */
    List<Task> getAll();

    /**
     * Updates an existing task in the repository.
     *
     * @param task The task to be updated.
     */
    void update(Task task);

    /**
     * Deletes a task from the repository by its ID.
     *
     * @param taskID The ID of the task to be deleted.
     */
    void delete(int taskID);

    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectID The ID of the project.
     * @return An Optional containing a list of tasks for the specified project, or empty if no tasks are found.
     */
    Optional<List<Task>> getAllProjectTasks(int projectID);

    /**
     * Retrieves a paginated list of tasks for a specific project.
     *
     * @param projectID The ID of the project.
     * @param page The page number (zero-based).
     * @param size The number of tasks per page.
     * @return An Optional containing a paginated list of tasks for the specified project, or empty if no tasks are found.
     */
    Optional<List<Task>> getPaginatedProjectTasks(int projectID, int page, int size);
}

