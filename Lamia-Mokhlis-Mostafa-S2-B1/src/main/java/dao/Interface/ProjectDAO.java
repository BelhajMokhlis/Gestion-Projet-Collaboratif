package dao.Interface;

import java.util.List;

import model.Project;

public interface ProjectDAO {
	
	void create(Project project);
    void update(Project project);
    void delete(int id);
    Project findById(int id);
    List<Project> findAll();
}
