package dao.Interface;

import model.Task;

import java.util.List;

/**
 * This interface defines the Data Access Object (DAO) operations for the Task entity.
 */
public interface TaskDAO {
    /**
     * Creates a new task in the data store.
     *
     * @param task The Task object to be created.
     */
    void create(Task task);

    /**
     * Retrieves a task from the data store by its ID.
     *
     * @param taskID The ID of the task to retrieve.
     * @return The Task object if found, or null if not found.
     */
    Task read(int taskID);

    /**
     * Updates an existing task in the data store.
     *
     * @param task The Task object with updated information.
     */
    void update(Task task);

    /**
     * Deletes a task from the data store by its ID.
     *
     * @param taskID The ID of the task to delete.
     */
    void delete(int taskID);

    /**
     * Retrieves all tasks from the data store.
     *
     * @return A List of all Task objects in the data store.
     */
    List<Task> getAll();
}
