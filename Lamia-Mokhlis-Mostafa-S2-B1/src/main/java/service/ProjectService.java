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

/**
 * Service class for managing Project entities.
 * This class provides methods for CRUD operations, validation, and various queries related to projects.
 */
public class ProjectService {

    private Map<String, Project> projectMap = new HashMap<>();
    private final ProjectRepository projectRepository;

    /**
     * Constructs a new ProjectService and initializes the project repository.
     * It also loads existing projects into the projectMap.
     */
    public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
        loadProjectsIntoMap(); 
    }

    /**
     * Validates a Project object.
     *
     * @param project The Project object to validate.
     * @return A list of error messages. An empty list indicates no errors.
     */
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
	
    /**
     * Creates a new project after validating it.
     *
     * @param project The Project object to create.
     * @throws IllegalArgumentException if the project fails validation.
     */
    public void createProject(Project project) throws IllegalArgumentException {
    	List<String> errors = validateProject(project);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
        projectRepository.create(project);
        loadProjectsIntoMap(); 
    }

    /**
     * Updates an existing project after validating it.
     *
     * @param project The Project object to update.
     * @throws IllegalArgumentException if the project fails validation.
     */
    public void updateProject(Project project)  throws IllegalArgumentException{
    	 List<String> errors = validateProject(project);
    	    if (!errors.isEmpty()) {
    	        throw new IllegalArgumentException(String.join(", ", errors));
    	    }
        projectRepository.update(project);
        loadProjectsIntoMap(); 
    }

    /**
     * Deletes a project by its ID.
     *
     * @param projectId The ID of the project to delete.
     */
    public void deleteProject(int projectId) {
        projectRepository.delete(projectId);
        loadProjectsIntoMap(); 
    }

    /**
     * Finds a project by its ID.
     *
     * @param projectId The ID of the project to find.
     * @return The found Project object, or null if not found.
     */
    public Project findProjectById(int projectId) {
        return projectRepository.findById(projectId);
    }

    /**
     * Retrieves all projects.
     *
     * @return A list of all Project objects.
     */
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
    
    /**
     * Gets the total count of all projects.
     *
     * @return The total number of projects.
     */
    public int getTotalProjectsCount() {
        return projectRepository.countAllProjects();
    }
    
    /**
     * Retrieves a paginated list of projects.
     *
     * @param page The page number.
     * @param pageSize The number of items per page.
     * @return A list of Project objects for the specified page.
     */
    public List<Project> getProjectsPag(int page, int pageSize) {
        return projectRepository.findProjectsWithPagination(page, pageSize);
    }
    
    /**
     * Loads all projects from the repository into the projectMap.
     * This method is private and used internally to keep the map updated.
     */
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

    /**
     * Searches for projects by title.
     *
     * @param title The title to search for.
     * @return A list of Project objects matching the search criteria.
     */
    public List<Project> searchProjectsByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return Arrays.asList(); 
        }
        return projectMap.values().stream()
                .filter(project -> project.getName().toLowerCase().contains(title.toLowerCase())) 
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the count of tasks for each project.
     *
     * @return A Map where the key is the project ID and the value is the number of tasks for that project.
     */
    public Map<Integer, Integer> getTaskCountForEachProject() {
        return projectRepository.countTasksPerProject();
    }
    
    /**
     * Retrieves the count of members for each project.
     *
     * @return A Map where the key is the project ID and the value is the number of members in that project.
     */
    public Map<Integer, Integer> getMemberCountForEachProject() {
        return projectRepository.countMembersPerProject();
    }

}
