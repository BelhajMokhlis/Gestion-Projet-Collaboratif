package projecttest;

import model.Project;
import service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ProjectServiceTest {


    private ProjectDAOImpl projectDAO;
    @Before
    public void setUp() {
    	  projectDAO = new ProjectDAOImpl();
    }


    @Test
    void testAddProject() {
        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.ACTIVE, 17);
        assertTrue(projectDAO.createProject(project)); 
    }

    @Test
    void testGetAllProjects() {
        Project project1 = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.ACTIVE, 17);
        Project project2 = new Project("Project B", "Description of Project B", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.ACTIVE, 18);
        projectDAO.createProject(project1);
        projectDAO.createProject(project2);
        
        List<Project> projects = projectDAO.findAllProjects(); 
        assertTrue(projects.contains(project1));
        assertTrue(projects.contains(project2));
    }

    @Test
    void testRemoveProject() {
        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.ACTIVE, 17);
        projectDAO.createProject(project);
        
        assertTrue(projectDAO.deleteProject(project.getId())); 
    }
    
    
    
    
    
    
}
