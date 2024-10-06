package projecttest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.impl.ProjectDAOImpl;
import model.Project;
import model.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;

class ProjectServiceTest {

    private ProjectDAOImpl projectDAO;

    @BeforeEach
    void setUp() {
        projectDAO = new ProjectDAOImpl();
    }

    @Test
    void testAddProject() {
        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.IN_PROGRESS, 17);
        projectDAO.create(project);
        assertNotNull(project.getId());
    }

    @Test
    void testGetAllProjects() {
        Project project1 = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.IN_PROGRESS, 17);
        Project project2 = new Project("Project B", "Description of Project B", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.IN_PROGRESS, 18);
        projectDAO.create(project1);
        projectDAO.create(project2);
        
        List<Project> projects = projectDAO.findAll();
        assertFalse(projects.contains(project1));
        assertFalse(projects.contains(project2));
    }

    @Test
    void testRemoveProject() {
        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusDays(30), ProjectStatus.IN_PREPARATION, 17);
        projectDAO.create(project);
        projectDAO.delete(project.getId());
        assertNull(projectDAO.findById(project.getId()));
    }
}
