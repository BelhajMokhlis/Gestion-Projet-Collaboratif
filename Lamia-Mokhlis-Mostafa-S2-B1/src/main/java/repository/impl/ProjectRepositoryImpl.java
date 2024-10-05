package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Interface.ProjectDAO;
import dao.impl.ProjectDAOImpl;
import model.Project;
import model.enums.ProjectStatus;
import repository.Interface.ProjectRepository;
import util.DatabaseConnection;

public class ProjectRepositoryImpl implements ProjectRepository  {

	 private final Connection connection;
	 private final ProjectDAO projectDAO = new ProjectDAOImpl();
	 
	  public ProjectRepositoryImpl() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	  }
	    @Override
	    public void create(Project project) {
	        projectDAO.create(project);
	    }

	    @Override
	    public void update(Project project) {
	        projectDAO.update(project);
	    }

	    @Override
	    public void delete(int id) {
	        projectDAO.delete(id);
	    }

	    @Override
	    public Project findById(int id) {
	        return projectDAO.findById(id);
	    }

	    @Override
	    public List<Project> findAll() {
	        return projectDAO.findAll();
	    }
	    

	    @Override
	    public List<Project> findProjectsWithPagination(int page, int pageSize) {
	        List<Project> projects = new ArrayList<>();
	        int offset = (page - 1) * pageSize;

	        String sql = "SELECT * FROM project LIMIT ? OFFSET ?";
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	             
	            pstmt.setInt(1, pageSize);
	            pstmt.setInt(2, offset);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                Project project = new Project();
	                project.setId(rs.getInt("id"));
	                project.setName(rs.getString("name"));
	                project.setDescription(rs.getString("description"));
	                project.setStartDate(rs.getDate("startDate").toLocalDate());
	                project.setEndDate(rs.getDate("endDate").toLocalDate());
	                project.setStatus(ProjectStatus.valueOf(rs.getString("status").toUpperCase()));
	                projects.add(project);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return projects;
	    }
	  

	    @Override
	    public int countAllProjects() {
	        int count = 0;
	        String sql = "SELECT COUNT(*) FROM project"; 
	        
	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet rs = statement.executeQuery()) {
	            if (rs.next()) {
	                count = rs.getInt(1); 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return count;
	    }
	    
	    public Map<Integer, Integer> countTasksPerProject() {
	        String sql = "SELECT project_id, COUNT(*) AS task_count FROM Task GROUP BY project_id";
	        Map<Integer, Integer> taskCounts = new HashMap<>();

	        try (PreparedStatement statement = connection.prepareStatement(sql);
		             ResultSet rs = statement.executeQuery()) {

	            while (rs.next()) {
	                int projectId = rs.getInt("project_id");
	                int taskCount = rs.getInt("task_count");
	                taskCounts.put(projectId, taskCount);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return taskCounts;
	    }
	    
	    public Map<Integer, Integer> countMembersPerProject() {
	        String sql = "SELECT project_id, COUNT(DISTINCT member_id) AS member_count FROM Task GROUP BY project_id";
	        Map<Integer, Integer> memberCounts = new HashMap<>();

	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet rs = statement.executeQuery()) {

	            while (rs.next()) {
	                int projectId = rs.getInt("project_id");
	                int memberCount = rs.getInt("member_count");
	                memberCounts.put(projectId, memberCount);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return memberCounts;
	    }


}
