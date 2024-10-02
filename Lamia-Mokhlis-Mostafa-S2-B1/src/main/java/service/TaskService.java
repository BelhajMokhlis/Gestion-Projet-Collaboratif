package service;

import model.Task;
import repository.Interface.TaskRepository;
import repository.impl.TaskRepositoryImpl;

import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepositoryImpl();

    public void createTask(Task task) {
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
}
