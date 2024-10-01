package dao;

import model.Project;

public interface ProjectDAO {

	boolean create(Project project);
    boolean update(Project project);
    boolean delete(int id);
}
