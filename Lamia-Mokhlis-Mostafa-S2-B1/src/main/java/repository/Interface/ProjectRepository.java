package repository.Interface;

import java.util.List;

import model.Project;

public interface ProjectRepository {

	void create(Project project);
    void update(Project project);
    void delete(int id);
    Project findById(int id);
    List<Project> findAll();
   int countAllProjects();
   List<Project> findProjectsWithPagination(int page, int pageSize);
}
