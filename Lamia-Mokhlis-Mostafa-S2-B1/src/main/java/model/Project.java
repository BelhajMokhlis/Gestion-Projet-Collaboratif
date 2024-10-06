package model;

import java.time.LocalDate;

import model.enums.ProjectStatus;

/**
 * Represents a project in the system.
 */
public class Project {
	
	private int id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private int teamId;
    

	/**
	 * Default constructor for Project.
	 */
	public Project() {
	
	}
	
	/**
	 * Constructs a new Project with the specified details.
	 *
	 * @param name        the name of the project
	 * @param description the description of the project
	 * @param startDate   the start date of the project
	 * @param endDate     the end date of the project
	 * @param status      the current status of the project
	 * @param teamId      the ID of the team assigned to the project
	 */
	public Project( String name, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status,int teamId) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.teamId = teamId;
	}

    /**
     * Gets the project ID.
     *
     * @return the project ID
     */
	public int getId() {
		return id;
	}

    /**
     * Sets the project ID.
     *
     * @param id the project ID to set
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Gets the project name.
     *
     * @return the project name
     */
	public String getName() {
		return name;
	}

    /**
     * Sets the project name.
     *
     * @param name the project name to set
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets the project description.
     *
     * @return the project description
     */
	public String getDescription() {
		return description;
	}

    /**
     * Sets the project description.
     *
     * @param description the project description to set
     */
	public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Gets the start date of the project.
     *
     * @return the start date
     */
	public LocalDate getStartDate() {
		return startDate;
	}

    /**
     * Sets the start date of the project.
     *
     * @param startDate the start date to set
     */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

    /**
     * Gets the end date of the project.
     *
     * @return the end date
     */
	public LocalDate getEndDate() {
		return endDate;
	}

    /**
     * Sets the end date of the project.
     *
     * @param endDate the end date to set
     */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

    /**
     * Gets the current status of the project.
     *
     * @return the status
     */
	public ProjectStatus getStatus() {
		return status;
	}

    /**
     * Sets the current status of the project.
     *
     * @param status the status to set
     */
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

    /**
     * Gets the team ID associated with the project.
     *
     * @return the team ID
     */
	public int getTeamId() {
		return teamId;
	}

    /**
     * Sets the team ID associated with the project.
     *
     * @param teamId the team ID to set
     */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
    
}
