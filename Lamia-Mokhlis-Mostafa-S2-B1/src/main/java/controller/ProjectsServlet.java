package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.enums.ProjectStatus;
import service.ProjectService;
import service.TeamService;

/**
 * Servlet implementation class ProjectsServlet
 * This servlet handles various operations related to projects, including listing, creating, editing, viewing, searching, and managing project statistics.
 */
public class ProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 /** The service for handling project-related operations. */
	 private ProjectService projectService;
	 /** The service for handling team-related operations. */
	 private TeamService teamService;

    /**
     * Default constructor for ProjectsServlet.
     * Initializes the ProjectService and TeamService.
     */
    public ProjectsServlet() {
    	this.projectService=new ProjectService();
    	 teamService = new TeamService(); 
    }

	/**
	 * Handles GET requests for project-related actions.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String action = request.getParameter("action");
	        
	        if ("list".equals(action)) {
	        	handleProjectList(request, response);
	        } else if ("edit".equals(action)) {
	            handleEditProject(request, response);
	        }else if ("view".equals(action)) {
	        	   viewProjectDetails(request, response);
	        }else if  ("search".equals(action)) {
	        	searchProjects(request, response); 
	        }else if ("stats".equals(action)) {
	        	 showProjectStats(request, response); 
	        } else if ("create".equals(action)) {
	        	handleCreateProject(request, response);
	        }
	}

	
	


	/**
	 * Handles POST requests for project-related actions.
	 *
	 * @param request  the HttpServletRequest object containing the client's request
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String action = request.getParameter("action");

	        if ("add".equals(action)) {
	            addProject(request, response);
	        } else if ("update".equals(action)) {
	            updateProject(request, response);
	        }else if ("delete".equals(action)) {
	            handleDeleteProject(request, response);  
	        }
	}
	
	/**
	 * Handles the request to create a new project.
	 * This method prepares the necessary data for the project creation form
	 * and forwards the request to the appropriate JSP page.
	 *
	 * @param request  the HttpServletRequest object containing the client's request
	 * @param response the HttpServletResponse object for sending the response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs during request processing
	 */
	private void handleCreateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teams", teamService.getAllTeams());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/addProject.jsp");
        dispatcher.forward(request, response);
		
	}
	
	/**
	 * Handles the editing of an existing project.
	 * Retrieves the project by ID, sets it as an attribute along with the list of teams,
	 * and forwards the request to the edit project JSP page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleEditProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectIdParam = request.getParameter("id");
          int  projectId = Integer.parseInt(projectIdParam);
       
        Project project = projectService.findProjectById(projectId);
        if (project != null) {
        	 request.setAttribute("project", project);
             request.setAttribute("teams", teamService.getAllTeams()); 
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/editProject.jsp");
        dispatcher.forward(request, response);
    }
	
	/**
	 * Displays project statistics.
	 * Retrieves task counts, member counts, and all projects,
	 * sets them as attributes, and forwards to the project stats JSP page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void showProjectStats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProjectService projectService = new ProjectService();
	    Map<Integer, Integer> taskCounts = projectService.getTaskCountForEachProject();
	    Map<Integer, Integer> memberCounts = projectService.getMemberCountForEachProject();
	    List<Project> projects = projectService.findAllProjects();	 
	    
	    request.setAttribute("memberCounts", memberCounts);
	    request.setAttribute("projects", projects);
	    request.setAttribute("taskCounts", taskCounts);	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/projectStats.jsp");
	    dispatcher.forward(request, response);
	}

	
	/**
	 * Searches for projects based on the provided title.
	 * Retrieves search results and forwards them to the project list JSP page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void searchProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	    List<Project> searchResults = projectService.searchProjectsByTitle(title);
	    request.setAttribute("projects", searchResults);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/project/projectList.jsp");
	    dispatcher.forward(request, response); 			
	}
	
	/**
	 * Retrieves and displays details of a specific project.
	 * Forwards the project details to the project details JSP page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void viewProjectDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("id"));
	    Project project = projectService.findProjectById(projectId);

	    if (project != null) {
	        request.setAttribute("project", project);
	        request.getRequestDispatcher("/jsp/project/projectDetails.jsp").forward(request, response);
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
	    }
	}
	
	
	/**
	 * Handles the request to list projects with pagination.
	 * Retrieves a page of projects and forwards them to the project list JSP page.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	private void handleProjectList(HttpServletRequest request, HttpServletResponse response) {
		 try {
		        int page = 1; 
		        int pageSize = 4; 
		        String pageParam = request.getParameter("page");
		        if (pageParam != null) {
		            try {
		                page = Integer.parseInt(pageParam);
		            } catch (NumberFormatException e) {
		                page = 1; 
		            }
		        }

		        List<Project> projects = projectService.getProjectsPag(page, pageSize);
		        int totalProjects = projectService.getTotalProjectsCount(); 
		        int totalPages = (int) Math.ceil((double) totalProjects / pageSize);

		        request.setAttribute("projects", projects);
		        request.setAttribute("currentPage", page);
		        request.setAttribute("totalPages", totalPages);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/project/projectList.jsp");
		        dispatcher.forward(request, response);
		    } catch (IOException e) {
		        e.printStackTrace(); 
		    } catch (ServletException e) {
		        e.printStackTrace(); 
		    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * Handles the deletion of a project.
	 * Deletes the project with the specified ID and redirects to the project list.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws IOException if an I/O error occurs
	 */
	private void handleDeleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String idStr = request.getParameter("id");
		    if (idStr != null) {
		        try {
		            int id = Integer.parseInt(idStr);
		            projectService.deleteProject(id);
		            request.getSession().setAttribute("msg", "Project deleted successfully.");

		        } catch (NumberFormatException e) {
		            request.getSession().setAttribute("msg", "Invalid project ID.");
		        }
		    } else {
		        request.getSession().setAttribute("msg", "Project ID is missing.");
		    }
		    response.sendRedirect(request.getContextPath() + "/ProjectsServlet?action=list");
	}
	
	/**
	 * Updates an existing project with the provided information.
	 * Validates the input and either updates the project or displays error messages.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void updateProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int projectId = Integer.parseInt(request.getParameter("id")); 
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String startDate = request.getParameter("startDate");
	    String endDate = request.getParameter("endDate");
	    String status = request.getParameter("status");
	    int teamId = Integer.parseInt(request.getParameter("teamId")); 

	    Project project = projectService.findProjectById(projectId); 

	    if (project != null) {
	        project.setName(name);
	        project.setDescription(description);
	        project.setStartDate(LocalDate.parse(startDate));
	        project.setEndDate(endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null); 
	        project.setStatus(ProjectStatus.valueOf(status)); 
	        project.setTeamId(teamId);
	        try {
	            projectService.updateProject(project);
	            response.sendRedirect(request.getContextPath() + "/ProjectsServlet?action=list");
	        } catch (IllegalArgumentException e) {
	            String errorMessage = e.getMessage();
	            request.setAttribute("errorMessage", errorMessage);
	            request.setAttribute("project", project); 
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/editProject.jsp"); 
	            dispatcher.forward(request, response);
	        }
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
	    }
	}

	  /**
	   * Adds a new project with the provided information.
	   * Validates the input and either creates the project or displays error messages.
	   *
	   * @param request  the HttpServletRequest object
	   * @param response the HttpServletResponse object
	   * @throws ServletException if a servlet-specific error occurs
	   * @throws IOException      if an I/O error occurs
	   */
	  private void addProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String name = request.getParameter("name");
		  String description = request.getParameter("description");
		  String startDate = request.getParameter("startDate");
		  String endDate = request.getParameter("endDate");
		  String status = request.getParameter("status");
		  int teamId = Integer.parseInt(request.getParameter("teamId")); 

		  Project project = new Project();
		  project.setName(name);
		  project.setDescription(description);
		  project.setStartDate(LocalDate.parse(startDate));
		  project.setEndDate(endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null); 
		  project.setStatus(ProjectStatus.valueOf(status)); 
		  project.setTeamId(teamId);

		  try {
			  projectService.createProject(project);
			  response.sendRedirect(request.getContextPath() +"/jsp/project/projectList.jsp");
		  } catch (IllegalArgumentException e) {
			  request.setAttribute("errorMessage", e.getMessage());
			  
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/project/addProject.jsp");
			  dispatcher.forward(request, response);
		  }
	  }
}