package service;

import java.util.List;

import model.Project;
import repository.Interface.ProjectRepository;
import repository.impl.ProjectRepositoryImpl;

public class ProjectService {



    private final ProjectRepository projectRepository;
  

	public ProjectService() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    public void createProject(Project project) {
        projectRepository.create(project);
    }

    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.delete(projectId);
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
    
    public List<Project> getProjects(int page, int pageSize) {
        return projectRepository.findProjectsWithPagination(page, pageSize);
    }
}
