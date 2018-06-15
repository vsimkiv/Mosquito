package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepoImplTest {

    @Autowired
    UserRepoImpl userRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void create() {
        User user = new User();
        user.setEmail("patriot02faqq@gmail.com");
        user.setPassword("ghsdf921jngjdfgsdfghhsdfhdf");
        user.setFirstName("Vitalik");
        user.setLastName("Makh");
        user.setConfirmed(true);

        User created = userRepo.create(user);
        assertNotNull(created.getId());

        User got = userRepo.read(created.getId());
        assertNotNull(got);
    }

    @Test
    public void read() {
        User user = userRepo.read(1L);
        assertNotNull(user);
    }

    @Test
    public void update() {
        User user = userRepo.read(1L);
        assertNotNull(user);
        user.setEmail("new email");
        userRepo.update(user);
    }

    @Test/*(expected = NullPointerException.class)*/
    public void delete() {
        User user = new User();
        user.setId(1L);
        userRepo.delete(1L);
        user = userRepo.read(1L);
        assertNull(user);
    }

    @Test
    public void getAll() {
        List<User> users = userRepo.getAll();
        assertNotNull(users);
    }
}