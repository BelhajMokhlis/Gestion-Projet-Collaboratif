package dao.Interface;

import java.util.List;

import model.Team;

/**
 * This interface defines the Data Access Object (DAO) operations for Team entities.
 */
public interface TeamDAO {
    /**
     * Adds a new team to the data store.
     *
     * @param team The Team object to be added.
     * @return true if the team was successfully added, false otherwise.
     */
    boolean addTeam(Team team);

    /**
     * Removes a team from the data store.
     *
     * @param team The Team object to be removed.
     * @return true if the team was successfully removed, false otherwise.
     */
    boolean removeTeam(Team team);

    /**
     * Updates an existing team in the data store.
     *
     * @param team The Team object with updated information.
     * @return true if the team was successfully updated, false otherwise.
     */
    boolean updateTeam(Team team);

    /**
     * Retrieves a team from the data store by its ID.
     *
     * @param id The ID of the team to retrieve.
     * @return The Team object if found, or null if not found.
     */
    Team getTeam(int id);

    /**
     * Retrieves all teams from the data store.
     *
     * @return A List of all Team objects in the data store.
     */
    List<Team> getAllTeams();
}
