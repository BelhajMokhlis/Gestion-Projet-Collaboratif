package controller;

import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import service.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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

        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueDateStr, formatter);
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Invalid due date format. Please use MM/DD/YYYY.");
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
            return;
        }

        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setPriority(priority);
        newTask.setDueDate(dueDate);
        newTask.setStatus(status);
        newTask.setCreationDate(LocalDate.now());

        try {
            taskService.createTask(newTask);
            response.sendRedirect(request.getContextPath() + "/jsp/tasks.jsp");
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }
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

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int projectID = Integer.parseInt(request.getParameter("projectID"));

        List<Task> tasks = taskService.getProjectTasks(1);

        // Set tasks as a request attribute to be accessed in JSP
        request.setAttribute("tasks", tasks);

        // Forward to JSP for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/taskList.jsp");
        dispatcher.forward(request, response);
    }

}
