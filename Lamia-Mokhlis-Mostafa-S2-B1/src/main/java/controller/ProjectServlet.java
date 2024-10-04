package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.enums.ProjectStatus;
import repository.impl.ProjectRepositoryImpl;
import service.ProjectService;

public class ProjectServlet extends HttpServlet  {


	 private ProjectService projectService;

	    @Override
	    public void init() throws ServletException {
	    	this.projectService=new ProjectService();
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        
	        if ("list".equals(action)) {
	            String msg ="hello";
	            request.setAttribute("msg", msg);
	                List<Project> projects = projectService.findAllProjects();
	                request.setAttribute("projects", projects);
	                RequestDispatcher dispatcher =request.getRequestDispatcher("/jsp/project/projectList.jsp");
	                dispatcher.forward(request, response) ;
	        } 
	    }
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if ("add".equals(action)) {
	            addProject(request, response);
	        }
	        // Handle other actions...
	    }
	    
	    private void addProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	  String name = request.getParameter("name");
	    	    String description = request.getParameter("description");
	    	    String startDate = request.getParameter("startDate");
	    	    String endDate = request.getParameter("endDate");
	    	    String status = request.getParameter("status");
	    	    int teamId = Integer.parseInt(request.getParameter("team_id")); 

	    	    Project project = new Project();
	    	    project.setName(name);
	    	    project.setDescription(description);
	    	    project.setStartDate(LocalDate.parse(startDate));
	    	    project.setEndDate(endDate != null && !endDate.isEmpty() ? LocalDate.parse(endDate) : null); 
	    	    project.setStatus(ProjectStatus.valueOf(status)); 
	    	    project.setTeamId(teamId);

	    	     
	    	    projectService.createProject(project); 
	    	    response.sendRedirect(request.getContextPath() +"/jsp/project/projectList.jsp");
	    }

}
