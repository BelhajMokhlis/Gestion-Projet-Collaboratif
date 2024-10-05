package service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Project;
import repository.Interface.ProjectRepository;
import repository.impl.ProjectRepositoryImpl;

public class ProjectService {


    private Map<String, Project> projectMap = new HashMap<>();
    private final ProjectRepository projectRepository;
  

	public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
        loadProjectsIntoMap(); 
    }

    public void createProject(Project project) {
        projectRepository.create(project);
        loadProjectsIntoMap(); 
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
        loadProjectsIntoMap(); 
    }

    public void deleteProject(int projectId) {
        projectRepository.delete(projectId);
        loadProjectsIntoMap(); 
    }

    public Project findProjectById(int projectId) {
        return projectRepository.findById(projectId);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
    
    public int getTotalProjectsCount() {
        return projectRepository.countAllProjects();
    }
    
    public List<Project> getProjectsPag(int page, int pageSize) {
        return projectRepository.findProjectsWithPagination(page, pageSize);
    }
    
    private void loadProjectsIntoMap() {
    	    List<Project> projects = projectRepository.findAll();

    	    // Debugging output to confirm projects are being loaded
    	    System.out.println("Loading projects from repository:");
    	    if (projects.isEmpty()) {
    	        System.out.println("No projects found.");
    	    } else {
    	        for (Project project : projects) {
    	            System.out.println("Project: " + project.getName() + ", Description: " + project.getDescription());
    	            projectMap.put(project.getName().toLowerCase(), project);
    	        }
    	    }
    }

    public List<Project> searchProjectsByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Arrays.asList(); 
        }
        return projectMap.values().stream()
                .filter(project -> project.getName().toLowerCase().contains(title.toLowerCase())) 
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
    	   ProjectService projectService = new ProjectService(); // Create an instance of ProjectService

           // Display the contents of the projectMap
           System.out.println("Projects in the map:");
           for (Map.Entry<String, Project> entry : projectService.projectMap.entrySet()) {
               System.out.println("Project Name: " + entry.getKey() + ", Project Details: " + entry.getValue());
           }
    }
}
