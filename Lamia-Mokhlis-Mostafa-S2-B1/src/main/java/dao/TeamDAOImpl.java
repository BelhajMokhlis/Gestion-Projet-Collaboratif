package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import model.Team;
import util.DatabaseConnection;

public class TeamDAOImpl implements TeamDAO {

    private Connection connection;

    public TeamDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean addTeam(Team team) {
        String sql = "INSERT INTO teams (id, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, team.getId());
            pstmt.setString(2, team.getName());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean removeTeam(Team team) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, team.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean updateTeam(Team team) {
        String sql = "UPDATE teams SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, team.getName());
            pstmt.setInt(2, team.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Team getTeam(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Team team = new Team();
                    team.setId(rs.getInt("id"));
                    team.setName(rs.getString("name"));
                    return team;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Team> getAllTeams() {
        String sql = "SELECT * FROM teams";
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
