package repository.Interface;

import java.util.List;
import java.util.Map;

import model.Project;

/**
 * This interface defines the contract for Project repository operations.
 */
public interface ProjectRepository {

	/**
	 * Creates a new project in the repository.
	 *
	 * @param project The project to be created.
	 */
	void create(Project project);

	/**
	 * Updates an existing project in the repository.
	 *
	 * @param project The project to be updated.
	 */
	void update(Project project);

	/**
	 * Deletes a project from the repository by its ID.
	 *
	 * @param id The ID of the project to be deleted.
	 */
	void delete(int id);

	/**
	 * Finds a project by its ID.
	 *
	 * @param id The ID of the project to find.
	 * @return The found project, or null if not found.
	 */
	Project findById(int id);

	/**
	 * Retrieves all projects from the repository.
	 *
	 * @return A list of all projects.
	 */
	List<Project> findAll();

	/**
	 * Counts the total number of projects in the repository.
	 *
	 * @return The total number of projects.
	 */
	int countAllProjects();

	/**
	 * Retrieves a paginated list of projects.
	 *
	 * @param page The page number.
	 * @param pageSize The number of projects per page.
	 * @return A list of projects for the specified page.
	 */
	List<Project> findProjectsWithPagination(int page, int pageSize);

	/**
	 * Counts the number of tasks for each project.
	 *
	 * @return A map where the key is the project ID and the value is the task count.
	 */
	Map<Integer, Integer> countTasksPerProject();

	/**
	 * Counts the number of members for each project.
	 *
	 * @return A map where the key is the project ID and the value is the member count.
	 */
	Map<Integer, Integer> countMembersPerProject();
}
