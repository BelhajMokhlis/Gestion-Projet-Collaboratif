package repository.impl;

import dao.impl.TaskDaoImpl;
import model.Task;
import repository.Interface.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the TaskRepository interface.
 * This class provides methods to perform CRUD operations on Task objects.
 */
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskDaoImpl taskDao;

    /**
     * Constructs a new TaskRepositoryImpl with a TaskDaoImpl instance.
     */
    public TaskRepositoryImpl() {
        this.taskDao = new TaskDaoImpl();
    }

    /**
     * Creates a new task in the repository.
     *
     * @param task The task to be created.
     */
    @Override
    public void create(Task task) {
        taskDao.create(task);
    }

    /**
     * Retrieves a task from the repository by its ID.
     *
     * @param taskID The ID of the task to retrieve.
     * @return The task with the specified ID.
     */
    @Override
    public Task read(int taskID) {
        return taskDao.read(taskID);
    }

    /**
     * Retrieves all tasks from the repository.
     *
     * @return A list of all tasks.
     */
    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    /**
     * Updates an existing task in the repository.
     *
     * @param task The task to be updated.
     */
    @Override
    public void update(Task task) {
        taskDao.update(task);
    }

    /**
     * Deletes a task from the repository by its ID.
     *
     * @param taskID The ID of the task to be deleted.
     */
    @Override
    public void delete(int taskID) {
        taskDao.delete(taskID);
    }

    /**
     * Retrieves all tasks associated with a specific project.
     *
     * @param projectID The ID of the project.
     * @return An Optional containing a list of tasks for the specified project, or an empty Optional if no tasks are found.
     */
    @Override
    public Optional<List<Task>> getAllProjectTasks(int projectID) {
        return Optional.of(taskDao.getAll().stream()
                .filter(task -> task.getProject().getId() == projectID)
                .collect(Collectors.toList()));
    }

    /**
     * Retrieves a paginated list of tasks associated with a specific project.
     *
     * @param projectID The ID of the project.
     * @param page The page number (1-based).
     * @param size The number of tasks per page.
     * @return An Optional containing a paginated list of tasks for the specified project, or an empty Optional if no tasks are found.
     */
    @Override
    public Optional<List<Task>> getPaginatedProjectTasks(int projectID, int page, int size) {
        return Optional.of(taskDao.getAll().stream()
                .filter(task -> task.getProject().getId() == projectID)
                .skip((page - 1) * size) // Skip the tasks for previous pages
                .limit(size)
                .collect(Collectors.toList()));
    }
}

