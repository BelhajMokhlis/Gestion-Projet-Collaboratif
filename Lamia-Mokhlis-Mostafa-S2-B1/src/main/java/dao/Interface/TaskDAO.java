package dao.Interface;

import model.Task;

import java.util.List;

public interface TaskDAO {
    void create(Task task);
    Task read(int taskID);
    void update(Task task);
    void delete(int taskID);
    List<Task> getAll();
}
