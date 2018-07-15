package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import com.softserve.mosquito.repo.api.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepoImplTest {

    @Autowired
    UserRepo userRepo;

    @Autowired
    SpecializationRepo specializationRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void CRUDTest() {
        //Create user test
        User user = new User();
        user.setEmail("patriot02faqq@gmail.com");
        user.setPassword("ghsdf921jngjdfgsdfghhsdfhdf");
        user.setFirstName("Vitalik");
        user.setLastName("Makh");
        user.setConfirmed(false);

        User created = userRepo.create(user);
        Long id = created.getId();
        assertNotNull(id);

        //Read user test
        User read = userRepo.read(id);
        assertNotNull(read);

        //Update user test
        read.setConfirmed(true);
        User updated = userRepo.update(read);
        assertEquals(true, updated.isConfirmed());

        //Delete test
        userRepo.delete(updated.getId());
        User notExisting = userRepo.read(updated.getId());
        assertNull(notExisting);
    }

    @Test
    public void specialReadingTest() {
        Specialization dev = specializationRepo.create(new Specialization("DEV"));
        Specialization ui = specializationRepo.create(new Specialization("UI"));
        for (int i = 1; i <= 12; i++) {
            User user = new User();
            user.setEmail("test_user" + i + "@gmail.com");
            user.setPassword("12345678");
            user.setFirstName("User_name" + i);
            user.setLastName("User_surname" + i);
            user.setConfirmed(true);
            Set<Specialization> specializations = new HashSet<>();
            specializations.add((i <= 6) ? dev : ui);
            user.setSpecializations(specializations);
            userRepo.create(user);
        }

        List<User> users = userRepo.readAll();
        assertNotNull(users);
        assertEquals(12, users.size());

        User readExisting = userRepo.readByEmail("test_user1@gmail.com");
        assertNotNull(readExisting);

        User readNotExisting = userRepo.readByEmail("no-email");
        assertNull(readNotExisting);
    }
}