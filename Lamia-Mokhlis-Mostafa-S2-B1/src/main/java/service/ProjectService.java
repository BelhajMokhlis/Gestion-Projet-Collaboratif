package service;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public List<String> validateProject(Project project) {
        List<String> errors = new ArrayList<>();

        if (project.getName() == null || project.getName().isEmpty()) {
            errors.add("Project Name is required.");
        }
        
        if (project.getDescription() == null || project.getDescription().isEmpty()) {
            errors.add("Description is required.");
        }


        LocalDate startDate = project.getStartDate();
        if (startDate == null) {
            errors.add("Start Date is required.");
        }


        if (project.getTeamId() <= 0) {
            errors.add("Please enter a valid Team ID.");
        }

        return errors;
    }
	
    public void createProject(Project project) throws IllegalArgumentException {
    	List<String> errors = validateProject(project);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
        projectRepository.create(project);
        loadProjectsIntoMap(); 
    }

    public void updateProject(Project project)  throws IllegalArgumentException{
    	 List<String> errors = validateProject(project);
    	    if (!errors.isEmpty()) {
    	        throw new IllegalArgumentException(String.join(", ", errors));
    	    }
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

    public Map<Integer, Integer> getTaskCountForEachProject() {
        return projectRepository.countTasksPerProject();
    }
    
    public Map<Integer, Integer> getMemberCountForEachProject() {
        return projectRepository.countMembersPerProject();
    }

}
