package dao.Interface;

import java.util.List;

import model.Project;

/**
 * This interface defines the Data Access Object (DAO) operations for the Project entity.
 */
public interface ProjectDAO {
	
	/**
	 * Creates a new project in the data store.
	 *
	 * @param project The Project object to be created.
	 */
	void create(Project project);

	/**
	 * Updates an existing project in the data store.
	 *
	 * @param project The Project object with updated information.
	 */
	void update(Project project);

	/**
	 * Deletes a project from the data store based on its ID.
	 *
	 * @param id The ID of the project to be deleted.
	 */
	void delete(int id);

	/**
	 * Retrieves a project from the data store based on its ID.
	 *
	 * @param id The ID of the project to be retrieved.
	 * @return The Project object if found, or null if not found.
	 */
	Project findById(int id);

	/**
	 * Retrieves all projects from the data store.
	 *
	 * @return A List of all Project objects in the data store.
	 */
	List<Project> findAll();
}
