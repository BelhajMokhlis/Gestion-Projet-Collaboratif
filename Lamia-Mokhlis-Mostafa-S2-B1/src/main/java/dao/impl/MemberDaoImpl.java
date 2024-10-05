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

public class MemberDaoImpl implements MemberDao {

    private Connection connection;
    private TeamDAO teamDAO;

    public MemberDaoImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        this.teamDAO = new TeamDAOImpl();
    }

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

    private Membre extractMembreFromResultSet(ResultSet rs) throws SQLException {
        Membre membre = new Membre();
        membre.setId(rs.getInt("id"));
        membre.setLastName(rs.getString("lastName"));
        membre.setFirstName(rs.getString("firstName"));
        membre.setEmail(rs.getString("email"));
        membre.setRole(MemberRole.valueOf(rs.getString("role").toUpperCase()));
        return membre;
    }

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
