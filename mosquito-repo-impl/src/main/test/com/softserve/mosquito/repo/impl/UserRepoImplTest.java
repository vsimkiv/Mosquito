package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.User;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

public class UserRepoImplTest {
    private static DataSource dataSource = new DataSourceForTests();
    private static Long createdUserId;

    @Test
    public void create() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);
        User user = new User();
        user.setFirstName("Vitalik");
        user.setLastName("Makh");
        user.setEmail("vitalik@test.com");
        user.setPassword("6fgh76sfd54g7s6fhfsf58ghflfj3jfg");

        user = repo.create(user);
        assertNotNull(user);
        assertNotNull(user.getId());
        createdUserId = user.getId();
    }

    @Test
    public void read() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);

        Long id = 46L;
        User user = repo.read(id);
        assertNotNull(user);
        assertEquals(user.getId(), id);
    }

    @Test
    public void update() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);
        String somethingNew = "something new";

        Long id = 46L;
        User user = repo.read(id);

        String oldName = user.getFirstName();
        user.setFirstName(somethingNew);

        repo.update(user);

        user = repo.read(id);
        assertEquals(user.getFirstName(), somethingNew);

        user.setFirstName(oldName);
        repo.update(user);
    }

    @Test
    public void delete() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);

        repo.delete(createdUserId);
        User user = repo.read(createdUserId);
        assertNull(user);
    }

    @Test
    public void readAll() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);

        List<User> users = repo.readAll();
        assertNotNull(users);
    }

    @Test
    public void readUserByEmail() throws Exception {
        UserRepoImpl repo = new UserRepoImpl(dataSource);

        String email = "if086soft@gmail.com";
        User user = repo.readUserByEmail(email);
        assertNotNull(user);
        assertEquals(user.getEmail(), email);
    }

}