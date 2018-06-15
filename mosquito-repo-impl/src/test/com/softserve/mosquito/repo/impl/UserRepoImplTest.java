package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
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
    public void CRUDTest() {
        //Create user test
        User user = new User();
        user.setEmail("patriot02faqq@gmail.com");
        user.setPassword("ghsdf921jngjdfgsdfghhsdfhdf");
        user.setFirstName("Vitalik");
        user.setLastName("Makh");
        user.setConfirmed(true);

        User created = userRepo.create(user);
        Long id = created.getId();
        assertNotNull(id);

        //Read user test
        User read = userRepo.read(id);
        assertNotNull(read);

        //Update user test
        String newEmail = "patriot@gmail.com";
        read.setEmail(newEmail);
        User updated = userRepo.update(read);
        assertEquals(newEmail, updated.getEmail());

        //Delete test
        userRepo.delete(updated.getId());
        read = userRepo.read(updated.getId());
        assertNull(read);
    }

    @Test
    public void specialReadingTest() {
        for (int i = 1; i <= 12; i++) {
            User user = new User();
            user.setEmail("test_user" + i + "@gmail.com");
            user.setPassword("12345678");
            user.setFirstName("User_name" + i);
            user.setLastName("User_surname" + i);
            user.setConfirmed(true);
            Set<Specialization> specializations = new HashSet<>();
            specializations.add((i <= 6) ? new Specialization("DEV") :
                    new Specialization("UI"));
            user.setSpecializations(specializations);
            userRepo.create(user);
        }

        List<User> users = userRepo.readAll();
        assertNotNull(users);
        assertEquals(12, users.size());

        /*users = userRepo.readBySpecializationId(1L);
        assertNotNull(users);
        assertEquals(6, users.size());
        assertEquals(users.iterator().next().getSpecializations().iterator().next().getTitle(), "DEV");
*/
        User readExisting = userRepo.readByEmail("test_user1@gmail.com");
        assertNotNull(readExisting);

        User readNotExisting = userRepo.readByEmail("no-email");
        assertNull(readNotExisting);
    }
}