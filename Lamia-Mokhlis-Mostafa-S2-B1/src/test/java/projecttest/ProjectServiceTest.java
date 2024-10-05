package projecttest;

import model.Project;
import service.ProjectService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ProjectServiceTest {


    private ProjectService projectService;

    @Before
    public void setUp() {
        projectService = new ProjectService(); 
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("This is a test project");

        projectService.createProject(project);
        
        Project retrievedProject = projectService.findProjectById(project.getId());
        assertNotNull("Project should be created", retrievedProject);
        assertEquals("Project name should match", "Test Project", retrievedProject.getName());
    }

}
