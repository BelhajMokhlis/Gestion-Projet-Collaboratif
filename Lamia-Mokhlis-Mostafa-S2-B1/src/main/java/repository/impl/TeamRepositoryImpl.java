package repository.impl;

import model.Team;

import java.util.List;

import dao.Interface.TeamDAO;
import dao.impl.TeamDAOImpl;
import repository.Interface.TeamRepository;

/**
 * Implementation of the TeamRepository interface.
 * This class provides methods to interact with the Team data access object.
 */
public class TeamRepositoryImpl implements TeamRepository {
    private TeamDAO teamDAOImpl;

    /**
     * Constructs a new TeamRepositoryImpl and initializes the TeamDAO.
     */
    public TeamRepositoryImpl() {
        this.teamDAOImpl = new TeamDAOImpl();
    }

    /**
     * Adds a new team to the repository.
     *
     * @param team The team to be added.
     * @return true if the team was successfully added, false otherwise.
     */
    @Override
    public boolean addTeam(Team team) {
        return teamDAOImpl.addTeam(team);
    }

    /**
     * Removes a team from the repository.
     *
     * @param team The team to be removed.
     * @return true if the team was successfully removed, false otherwise.
     */
    @Override
    public boolean removeTeam(Team team) {
        return teamDAOImpl.removeTeam(team);
    }

    /**
     * Updates an existing team in the repository.
     *
     * @param team The team to be updated.
     * @return true if the team was successfully updated, false otherwise.
     */
    @Override
    public boolean updateTeam(Team team) {
        return teamDAOImpl.updateTeam(team);
    }
    
    /**
     * Retrieves a team by its ID.
     *
     * @param id The ID of the team to retrieve.
     * @return The team with the specified ID, or null if not found.
     */
    @Override
    public Team getTeam(int id) {
        return teamDAOImpl.getTeam(id);
    }

    /**
     * Retrieves all teams from the repository.
     *
     * @return A list of all teams in the repository.
     */
    @Override
    public List<Team> getAllTeams() {
        return teamDAOImpl.getAllTeams();
    }
}
