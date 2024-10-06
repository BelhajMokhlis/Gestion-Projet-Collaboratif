package taskTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.impl.TaskDaoImpl;
import dao.Interface.TaskDAO;
import model.Membre;
import model.Project;
import model.Task;
import model.enums.TaskPriority;
import model.enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;

class TaskServiceTest {

    private TaskDAO taskDAO;

    @BeforeEach
    void setUp() {
        taskDAO = new TaskDaoImpl();
    }

    @Test
    void testAddTask() {
        Project project = new Project();
        project.setId(1);
        Membre member = new Membre();
        member.setId(1);
        Task task = new Task( "A", "desciption of A", TaskPriority.LOW,TaskStatus.IN_PROGRESS,LocalDate.now(), LocalDate.now().plusDays(7),project,member);
        taskDAO.create(task);
        assertNotNull(task.getTaskID());
    }

    @Test
    void testGetAllTasks() {
        Project project = new Project();
        project.setId(1);
        Membre member = new Membre();
        member.setId(1);
        Task task1 = new Task("Task A", "Description of Task A", TaskPriority.LOW, TaskStatus.IN_PROGRESS, LocalDate.now(), LocalDate.now().plusDays(7),project,member);
        Task task2 = new Task("Task B", "Description of Task B", TaskPriority.LOW, TaskStatus.IN_PROGRESS, LocalDate.now(), LocalDate.now().plusDays(14),project,member);
        taskDAO.create(task1);
        taskDAO.create(task2);
        
        List<Task> tasks = taskDAO.getAll();
        assertFalse(tasks.contains(task1));
        assertFalse(tasks.contains(task2));
    }

    @Test
    void testRemoveTask() {
        Project project = new Project();
        project.setId(1);
        Membre member = new Membre();
        member.setId(1);
        Task task = new Task("Task A", "Description of Task A", TaskPriority.LOW,TaskStatus.IN_PROGRESS,LocalDate.now(), LocalDate.now().plusDays(7),project,member);
        taskDAO.create(task);
        assertNotNull(task.getTaskID());
    }
}
