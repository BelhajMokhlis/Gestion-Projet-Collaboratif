package controller;

import model.Membre;
import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import service.MemberService;
import service.ProjectService;
import service.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TaskServlet
 */
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final TaskService taskService;
    private final ProjectService projectService = new ProjectService();
    private final MemberService memberService = new MemberService();


    /**
     * Default constructor for TaskServlet.
     * Initializes the TaskService.
     */
    public TaskServlet() {
        this.taskService = new TaskService();
    }

    /**
     * Handles GET requests for task-related actions.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
       
        switch (action) {
        case "create":
            createTaskForm(request, response);
            break;
        case "edit":
            editTask(request, response);
            break;
        case "list":
        	listTasks(request, response);
            break;
        default:
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            break;
        }
    }

    /**
     * Handles POST requests for task-related actions.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
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
            case "assignMember":
                assignMember(request, response);
                break;
            case "updateStatus":
                updateTaskStatus(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }
    
    /**
     * Prepares and forwards the request to the task creation form.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void createTaskForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        
        request.setAttribute("projectID", projectID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/addTask.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * Handles the insertion of a new task.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void insertTask(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int projectID = Integer.parseInt(request.getParameter("projectID"));
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

        Project project = projectService.findProjectById(projectID);
        
        newTask.setProject(project);

        try {
            taskService.createTask(newTask);
            response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectID=" + projectID);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }
    }

    /**
     * Prepares and forwards the request to the task editing form.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void editTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskID = Integer.parseInt(request.getParameter("taskID"));

        Task task = taskService.getTask(taskID);

        request.setAttribute("task", task);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/editTask.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the update of an existing task.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priorityStr = request.getParameter("priority");
        String dueDateStr = request.getParameter("dueDate");

        TaskPriority priority = TaskPriority.valueOf(priorityStr.toUpperCase());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueDateStr, formatter);
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Invalid due date format. Please use MM/DD/YYYY.");
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
            return;
        }

        Task task = taskService.getTask(taskID);
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDueDate(dueDate);

        taskService.updateTask(task);

        // Redirect to the task list page after update
        response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectID=" + task.getProject().getId());
    }

    /**
     * Handles the deletion of a task.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void deleteTask(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int taskID = Integer.parseInt(request.getParameter("taskID"));

        try {
            taskService.deleteTask(taskID); 
            response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectID=" + request.getParameter("projectID"));
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error deleting task: " + e.getMessage());
            request.getRequestDispatcher("/jsp/errorPage.jsp").forward(request, response);
        }
    }
    
    /**
     * Handles the assignment of a member to a task.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void assignMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        String memberIDStr = request.getParameter("memberID");

        Task task = taskService.getTask(taskID);

        // Check if a member is selected
        if (memberIDStr != null && !memberIDStr.isEmpty()) {
            int memberID = Integer.parseInt(memberIDStr);
            Membre member = memberService.getMember(memberID);
            task.setMember(member);  // Assign the member to the task
        } else {
            task.setMember(null);  // Unassigns the member if no selection
        }

        taskService.updateTask(task);

        int projectID = task.getProject().getId();
        response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectID=" + projectID);
    }

    /**
     * Retrieves and displays a paginated list of tasks for a project.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        int page = 1;
        int size = 5;
        
        if (request.getParameter("page") != null) {   
           page = Integer.parseInt(request.getParameter("page"));                      
        }

        if (request.getParameter("size") != null) { 
           size = Integer.parseInt(request.getParameter("size")); 
        }

        Project project = projectService.findProjectById(projectID);
        List<Membre> members = memberService.getMembersByTeam(project.getTeamId());

        List<Task> tasks = taskService.getPaginatedProjectTasks(projectID, page, size);
        int totalTasks = taskService.getTotalTasksForProject(projectID);
        int totalPages = (int) Math.ceil((double) totalTasks / size);

        request.setAttribute("tasks", tasks);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("projectID", projectID);
        request.setAttribute("members", members);

        // Forward to JSP for rendering
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/taskList.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * Handles the update of a task's status.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void updateTaskStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taskID = Integer.parseInt(request.getParameter("taskID"));
        String statusStr = request.getParameter("status");

        TaskStatus status = TaskStatus.valueOf(statusStr.toUpperCase());

        Task task = taskService.getTask(taskID);
        task.setStatus(status);

        taskService.updateTask(task);

        // Redirect to the task list page after status update
        response.sendRedirect(request.getContextPath() + "/tasks?action=list&projectID=" + task.getProject().getId());
    }

}