package dao.impl;

import dao.Interface.TaskDAO;
import dao.ProjectDAOImpl;
import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void create(Task task) {
        String query = "INSERT INTO task (title, description, priority, status, creationDate, dueDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getPriority().name());
            ps.setString(4, task.getStatus().name());
            ps.setDate(5, Date.valueOf(task.getCreationDate()));
            ps.setDate(6, Date.valueOf(task.getDueDate()));

            ps.executeUpdate();
            System.out.println("Task created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating task: " + e.getMessage());
        }
    }

    @Override
    public Task read(int taskID) {
        String query = "SELECT * FROM task WHERE id = ?";
        Task task = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, taskID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                task = new Task();
                task.setTaskID(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(TaskPriority.valueOf(rs.getString("priority")));
                task.setStatus(TaskStatus.valueOf(rs.getString("status")));
                task.setCreationDate(rs.getDate("creationDate").toLocalDate());
                task.setDueDate(rs.getDate("dueDate").toLocalDate());
            }
            System.out.println("Task retrieved successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving task: " + e.getMessage());
        }
        return task;
    }

    @Override
    public void update(Task task) {
        String query = "UPDATE task SET title = ?, description = ?, priority = ?, status = ?, creationDate = ?, dueDate = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getPriority().name());
            ps.setString(4, task.getStatus().name());
            ps.setDate(5, Date.valueOf(task.getCreationDate()));
            ps.setDate(6, Date.valueOf(task.getDueDate()));
            ps.setInt(7, task.getTaskID());

            ps.executeUpdate();
            System.out.println("Task updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }

    @Override
    public void delete(int taskID) {
        String query = "DELETE FROM task WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, taskID);
            ps.executeUpdate();
            System.out.println("Task deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskID(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setPriority(TaskPriority.fromString(rs.getString("priority")));
                task.setStatus(TaskStatus.fromString(rs.getString("status")));
                task.setCreationDate(rs.getDate("creationDate").toLocalDate());
                task.setDueDate(rs.getDate("dueDate").toLocalDate());

                int projectID = rs.getInt("project_id");
                ProjectDAOImpl projectDao = new ProjectDAOImpl();
                Project project = projectDao.findById(projectID);
                task.setProject(project);

                tasks.add(task);
            }
            System.out.println("Retrieved all tasks successfully!");
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;
    }
}

