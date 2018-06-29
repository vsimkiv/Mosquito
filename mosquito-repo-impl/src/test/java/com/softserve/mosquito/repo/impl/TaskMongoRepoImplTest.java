package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.*;
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
public class TaskMongoRepoImplTest {

    @Autowired
    TaskRepoImpl taskRepo;
    @Autowired
    UserRepoImpl userRepo;

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
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
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
        User user1 = userRepo.create(new User("email1", "name1", "surname1", "password1"));
        User user2 = userRepo.create(new User("email1", "name1", "surname1", "password1"));
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
        assertTrue(subTasks.iterator().next() instanceof Task);

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