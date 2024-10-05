package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.Interface.ProjectDAO;
import model.Project;
import model.enums.ProjectStatus;
import util.DatabaseConnection;



public class ProjectDAOImpl implements  ProjectDAO{

	  private final Connection connection;
	  
	  public ProjectDAOImpl() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	  }
	  
	  @Override
	    public void create(Project project) {
	        String sql = "INSERT INTO Project (name, description, startDate, endDate, status, team_id) VALUES (?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
	            pstmt.setString(1, project.getName());
	            pstmt.setString(2, project.getDescription());
	            pstmt.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
	            pstmt.setDate(4, project.getEndDate() != null ? java.sql.Date.valueOf(project.getEndDate()) : null);
	            pstmt.setString(5, project.getStatus().name());
	            pstmt.setInt(6, project.getTeamId());

	            pstmt.executeUpdate();

	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    project.setId(generatedKeys.getInt(1));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  @Override
	    public void update(Project project) {
	        String sql = "UPDATE Project SET name = ?, description = ?, startDate = ?, endDate = ?, status = ?, team_id = ? WHERE id = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, project.getName());
	            pstmt.setString(2, project.getDescription());
	            pstmt.setDate(3, java.sql.Date.valueOf(project.getStartDate()));
	            pstmt.setDate(4, project.getEndDate() != null ? java.sql.Date.valueOf(project.getEndDate()) : null);
	            pstmt.setString(5, project.getStatus().name());
	            pstmt.setInt(6, project.getTeamId());
	            pstmt.setInt(7, project.getId());

	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Project with ID " + project.getId() + " has been updated successfully.");
	            } else {
	                System.out.println("No project found with ID " + project.getId() + ".");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  @Override
	    public void delete(int id) {
	        String sql = "DELETE FROM Project WHERE id = ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Project with ID " + id + " has been deleted successfully.");
	            } else {
	                System.out.println("No project found with ID " + id + ".");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	   @Override
	    public Project findById(int id) {
	        String sql = "SELECT * FROM Project WHERE id = ?";
	        Project project = null;
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setInt(1, id);
	            ResultSet resultSet = pstmt.executeQuery();

	            if (resultSet.next()) {
	                project = new Project();
	                project.setId(resultSet.getInt("id"));
	                project.setName(resultSet.getString("name"));
	                project.setDescription(resultSet.getString("description"));
	                project.setStartDate(resultSet.getDate("startDate").toLocalDate());
	                project.setEndDate(resultSet.getDate("endDate") != null ? resultSet.getDate("endDate").toLocalDate() : null);
	                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status").toUpperCase()));
	                project.setTeamId(resultSet.getInt("team_id"));
	            } else {
	                System.out.println("No project found with ID " + id + ".");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return project;
	    }


	    @Override
	    public List<Project> findAll() {
	        List<Project> projects = new ArrayList<>();
	        String sql = "SELECT * FROM Project";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql);
	             ResultSet resultSet = pstmt.executeQuery()) {

	            while (resultSet.next()) {
	                Project project = new Project();
	                project.setId(resultSet.getInt("id"));
	                project.setName(resultSet.getString("name"));
	                project.setDescription(resultSet.getString("description"));
	                project.setStartDate(resultSet.getDate("startDate").toLocalDate());
	                project.setEndDate(resultSet.getDate("endDate") != null ? resultSet.getDate("endDate").toLocalDate() : null);
	                project.setStatus(ProjectStatus.valueOf(resultSet.getString("status").toUpperCase()));
	                project.setTeamId(resultSet.getInt("team_id"));
	                projects.add(project);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return projects;
	    }

	

}
