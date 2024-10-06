package taskTest;

import dao.impl.TaskDaoImpl;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;
import model.Project;
import model.Team;
import model.Membre;
import model.enums.MemberRole;
import model.enums.ProjectStatus;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskServiceTest {

    private TaskDaoImpl taskDAO;

    @Before
    public void setUp() {
        taskDAO = new TaskDaoImpl();
    }

    @Test
    public void testAddTask() {

        List<Membre> members = new ArrayList<>();
        Team team = new Team(1, "Development Team", members);

        Membre member = new Membre(1, "John", "Doe", "john.doe@example.com", MemberRole.DEVELOPER, team);
        members.add(member);

        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusMonths(1), ProjectStatus.IN_PROGRESS, team.getId());

        Task task = new Task(1, "Task A", "Description of Task A", TaskPriority.MEDIUM, TaskStatus.TO_DO, LocalDate.now(), LocalDate.now().plusDays(7), project, member);

        assertTrue(taskDAO.create(task));
    }

    @Test
    public void testGetAllTasks() {

        List<Membre> members = new ArrayList<>();
        Team team = new Team(1, "Development Team", members);

        Membre member1 = new Membre(1, "John", "Doe", "john.doe@example.com", MemberRole.DEVELOPER, team);
        Membre member2 = new Membre(2, "Jane", "Doe", "jane.doe@example.com", MemberRole.DESIGNER, team);
        members.add(member1);
        members.add(member2);


        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusMonths(1), ProjectStatus.CANCELED, team.getId());

        // Creating Tasks
        Task task1 = new Task(1, "Task A", "Description of Task A", TaskPriority.MEDIUM, TaskStatus.TO_DO, LocalDate.now(), LocalDate.now().plusDays(7), project, member1);
        Task task2 = new Task(2, "Task B", "Description of Task B", TaskPriority.HIGH, TaskStatus.IN_PROGRESS, LocalDate.now(), LocalDate.now().plusDays(10), project, member2);

        taskDAO.create(task1);
        taskDAO.create(task2);

        List<Task> tasks = taskDAO.getAll();
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    public void testRemoveTask() {
        List<Membre> members = new ArrayList<>();
        Team team = new Team(1, "Development Team", members);

        Membre member = new Membre(1, "John", "Doe", "john.doe@example.com", MemberRole.DEVELOPER, team);
        members.add(member);

        Project project = new Project("Project A", "Description of Project A", LocalDate.now(), LocalDate.now().plusMonths(1), ProjectStatus.ON_HOLD, team.getId());

        Task task = new Task(1, "Task A", "Description of Task A", TaskPriority.MEDIUM, TaskStatus.TO_DO, LocalDate.now(), LocalDate.now().plusDays(7), project, member);

        taskDAO.create(task);

        assertTrue(taskDAO.delete(task.getTaskID()));
    }
}
