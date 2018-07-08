package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.*;
import com.softserve.mosquito.repo.api.StatusRepo;
import com.softserve.mosquito.repo.api.TaskRepo;
import com.softserve.mosquito.repo.api.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskRepoImplTest {

    @Autowired
    TaskRepo taskRepo;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void CRUDTest() {
        //Create task test
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        user = userRepo.create(user);
        Priority priority = new Priority(1L,"middle");
        Status status = statusRepo.create(new Status(1L, "TODO"));
        Task task = Task.builder().name("Test task").estimation(estimation)
                .owner(user).worker(user).priority(priority).status(status).build();

        Task created = taskRepo.create(task);
        Long id = created.getId();
        assertNotNull(id);

        //Update task test
        String newName = "new test name";
        created.setName(newName);
        Task updated = taskRepo.update(created);
        assertNotNull(updated);
        assertEquals(newName, updated.getName());

        //Read task test
        Task read = taskRepo.read(id);
        assertNotNull(read);

        //Delete task test
        taskRepo.delete(id);
        Task notExisting = taskRepo.read(id);
        assertNull(notExisting);
    }

    @Test
    public void specialRead() {
        User user1 = userRepo.create(User.builder().email("email1").password("password1")
                .firstName("name1").lastName("surname1").build());
        User user2 = userRepo.create(User.builder().email("email2").password("password2")
                .firstName("name2").lastName("surname2").build());
        Task task = Task.builder().name("Test project").worker(user1).owner(user1).build();
        task = taskRepo.create(task);
        for (int i = 0; i < 5; i++) {
            Task subTask = Task.builder().name("Test sub task" + i).parentTask(task)
                    .worker(user2).owner(user2).build();
            taskRepo.create(subTask);

            Task project = Task.builder().name("Test project" + (i + 1)).build();
            taskRepo.create(project);
        }

        List<Task> subTasks = taskRepo.getSubTasks(1L);
        assertNotNull(subTasks);
        assertEquals(5, subTasks.size());
        assertNotNull(subTasks.iterator().next());

        List<Task> projects = taskRepo.getAllProjects();
        assertNotNull(projects);
        assertEquals(6, projects.size());

        Task read = taskRepo.getByName("Test project");
        assertNotNull(read);

        List<Task> tasks = taskRepo.getByOwner(user2.getId());
        assertNotNull(tasks);
        assertEquals(5, tasks.size());

        tasks = taskRepo.getByWorker(user2.getId());
        assertNotNull(tasks);
        assertEquals(5, tasks.size());

        tasks = taskRepo.getProjectsByOwner(user1.getId());
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }
}