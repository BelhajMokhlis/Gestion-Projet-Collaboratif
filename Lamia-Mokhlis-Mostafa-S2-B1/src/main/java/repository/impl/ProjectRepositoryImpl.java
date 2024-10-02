package repository.impl;

import java.util.List;
import dao.ProjectDAO;
import dao.ProjectDAOImpl;
import model.Project;

public class ProjectRepositoryImpl implements ProjectRepository  {

	 private final ProjectDAO projectDAO = new ProjectDAOImpl();

	    @Override
	    public void create(Project project) {
	        projectDAO.create(project);
	    }

	    @Override
	    public void update(Project project) {
	        projectDAO.update(project);
	    }

	    @Override
	    public void delete(int id) {
	        projectDAO.delete(id);
	    }

	    @Override
	    public Project findById(int id) {
	        return projectDAO.findById(id);
	    }

	    @Override
	    public List<Project> findAll() {
	        return projectDAO.findAll();
	    }
}
