package repository.Interface;

import model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void create(Task task);
    Task read(int taskID);
    List<Task> getAll();
    void update(Task task);
    void delete(int taskID);
    Optional<List<Task>> getAllProjectTasks(int projectID);
}

