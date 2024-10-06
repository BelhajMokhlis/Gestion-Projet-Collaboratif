package service;

import repository.impl.TeamRepositoryImpl;
import model.Team;

import java.util.List;

/**
 * Service class for managing Team operations.
 */
public class TeamService {
    private TeamRepositoryImpl teamRepositoryImpl;

    /**
     * Constructs a new TeamService with a TeamRepositoryImpl instance.
     */
    public TeamService() {
        this.teamRepositoryImpl = new TeamRepositoryImpl();
    }

    /**
     * Adds a new team to the repository.
     *
     * @param team The team to be added
     * @return true if the team was successfully added, false otherwise
     */
    public boolean addTeam(Team team) {
        return teamRepositoryImpl.addTeam(team);
    }

    /**
     * Removes a team from the repository.
     *
     * @param team The team to be removed
     * @return true if the team was successfully removed, false otherwise
     */
    public boolean removeTeam(Team team) {
        return teamRepositoryImpl.removeTeam(team);
    }

    /**
     * Updates an existing team in the repository.
     *
     * @param team The team to be updated
     * @return true if the team was successfully updated, false otherwise
     */
    public boolean updateTeam(Team team) {
        return teamRepositoryImpl.updateTeam(team);
    }

    /**
     * Retrieves a team by its ID.
     *
     * @param id The ID of the team to retrieve
     * @return The team with the specified ID, or null if not found
     */
    public Team getTeam(int id) {
        return teamRepositoryImpl.getTeam(id);
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return A list of all teams
     */
    public List<Team> getAllTeams() {
        return teamRepositoryImpl.getAllTeams();
    }
}
