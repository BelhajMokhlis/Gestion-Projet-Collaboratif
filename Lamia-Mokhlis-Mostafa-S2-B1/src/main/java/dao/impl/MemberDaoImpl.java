package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.Interface.MemberDao;
import dao.Interface.TeamDAO;
import model.Membre;
import model.Team;
import model.enums.MemberRole;
import util.DatabaseConnection;

/**
 * Implementation of the MemberDao interface for database operations related to members.
 */
public class MemberDaoImpl implements MemberDao {

    private Connection connection;
    private TeamDAO teamDAO;

    /**
     * Constructs a new MemberDaoImpl and initializes the database connection and TeamDAO.
     */
    public MemberDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.teamDAO = new TeamDAOImpl();
    }

    /**
     * Adds a new member to the database.
     *
     * @param membre The member to be added.
     * @return true if the member was successfully added, false otherwise.
     */
    @Override
    public boolean addMember(Membre membre) {
        String sql = "INSERT INTO `member`( `lastName`, `firstName`, `email`, `role`, `team_id`) VALUES (?,?,?,?,?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, membre.getLastName());
            pstmt.setString(2, membre.getFirstName());
            pstmt.setString(3, membre.getEmail());
            pstmt.setString(4, membre.getRole().toString());
            pstmt.setInt(5,membre.getTeam().getId()); 
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a member from the database.
     *
     * @param membre The member to be removed.
     * @return true if the member was successfully removed, false otherwise.
     */
    @Override
    public boolean removeMember(Membre membre) {
        String sql = "DELETE FROM member WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, membre.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing member's information in the database.
     *
     * @param membre The member with updated information.
     * @return true if the member was successfully updated, false otherwise.
     */
    @Override
    public boolean updateMember(Membre membre) {
        String sql = "UPDATE member SET lastName = ?, firstName = ?, email = ?, role = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, membre.getLastName());
            pstmt.setString(2, membre.getFirstName());
            pstmt.setString(3, membre.getEmail());
            pstmt.setString(4, membre.getRole().toString());
            pstmt.setInt(5, membre.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a member from the database by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return The member with the specified ID, or null if not found.
     */
    @Override
    public Membre getMember(int id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractMembreFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all members from the database.
     *
     * @return A list of all members.
     */
    @Override
    public List<Membre> getAllMembers() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                membres.add(extractMembreFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    /**
     * Extracts member information from a ResultSet and creates a Membre object.
     *
     * @param rs The ResultSet containing member data.
     * @return A Membre object populated with data from the ResultSet.
     * @throws SQLException if a database access error occurs.
     */
    private Membre extractMembreFromResultSet(ResultSet rs) throws SQLException {
        Membre membre = new Membre();
        membre.setId(rs.getInt("id"));
        membre.setLastName(rs.getString("lastName"));
        membre.setFirstName(rs.getString("firstName"));
        membre.setEmail(rs.getString("email"));
        membre.setRole(MemberRole.valueOf(rs.getString("role").toUpperCase()));
        return membre;
    }

    /**
     * Retrieves all members belonging to a specific team.
     *
     * @param teamId The ID of the team.
     * @return A list of members belonging to the specified team.
     */
    @Override
    public List<Membre> getMembersByTeam(int teamId) {
        List<Membre> members= new ArrayList<Membre>();
        String sql = "SELECT * FROM member WHERE team_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, teamId);
            try (ResultSet rs = pstmt.executeQuery()) { 
                while (rs.next()) {
                    members.add(extractMembreFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return members;
    }


 
    


}
