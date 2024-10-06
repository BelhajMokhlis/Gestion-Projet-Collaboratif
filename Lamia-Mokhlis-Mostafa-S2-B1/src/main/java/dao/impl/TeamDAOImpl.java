package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import dao.Interface.TeamDAO;

import java.util.ArrayList;

import model.Team;
import util.DatabaseConnection;


/**
 * Implementation of the TeamDAO interface for database operations related to Team entities.
 */
public class TeamDAOImpl implements TeamDAO {

    private Connection connection;

    /**
     * Constructs a new TeamDAOImpl and initializes the database connection.
     */
    public TeamDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Adds a new team to the database.
     *
     * @param team The Team object to be added.
     * @return true if the team was successfully added, false otherwise.
     */
    @Override
    public boolean addTeam(Team team) {
        String sql = "INSERT INTO team (name) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, team.getName());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    team.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a team from the database.
     *
     * @param team The Team object to be removed.
     * @return true if the team was successfully removed, false otherwise.
     */
    @Override
    public boolean removeTeam(Team team) {
        String sql = "DELETE FROM team WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, team.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing team in the database.
     *
     * @param team The Team object with updated information.
     * @return true if the team was successfully updated, false otherwise.
     */
    @Override
    public boolean updateTeam(Team team) {
        String sql = "UPDATE team SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, team.getName());
            pstmt.setInt(2, team.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a team from the database by its ID.
     *
     * @param id The ID of the team to retrieve.
     * @return The Team object if found, null otherwise.
     */
    @Override
    public Team getTeam(int id) {
        String sql = "SELECT * FROM team WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Team team = new Team();
                    team.setId(rs.getInt("id"));
                    team.setName(rs.getString("name"));
                    return team;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        
      
    

    /**
     * Retrieves all teams from the database.
     *
     * @return A List of all Team objects in the database.
     */
    @Override
    public List<Team> getAllTeams() {
        String sql = "SELECT * FROM team";
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

}
