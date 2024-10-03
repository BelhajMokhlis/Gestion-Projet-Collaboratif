package controller;

import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import service.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TaskServlet
 */
//@WebServlet("/")
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TaskService taskService;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        this.taskService = new TaskService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("get".equals(action)) {
            getTaskByID(request, response);
        } else {
            listTasks(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertTask(request, response);
                break;
            case "update":
                updateTask(request, response);
                break;
            case "delete":
                deleteTask(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }


    private void insertTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priorityStr = request.getParameter("priority");
        String dueDateStr = request.getParameter("dueDate");

        TaskPriority priority = TaskPriority.valueOf(priorityStr.toUpperCase());
        TaskStatus status = TaskStatus.TO_DO;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setPriority(priority);
        newTask.setCreationDate(LocalDate.now());
        newTask.setDueDate(dueDate);
        newTask.setStatus(status);

        taskService.createTask(newTask);

        response.sendRedirect(request.getContextPath() + "/tasks");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    private void getTaskByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
