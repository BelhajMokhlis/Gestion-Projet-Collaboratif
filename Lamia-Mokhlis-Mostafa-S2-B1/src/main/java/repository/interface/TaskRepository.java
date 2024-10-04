package repository.Interface;

import model.Task;

import java.util.List;

public interface TaskRepository {
    void create(Task task);
    Task read(int taskID);
    List<Task> getAll();
    void update(Task task);
    void delete(int taskID);
}

