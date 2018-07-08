package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PriorityRepoImplTest {
    @Autowired
    PriorityRepo priorityRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void GeneralTest() {
        //Create priority test:
        Priority prior = new Priority();
        prior.setTitle("Low");
        Priority prior2 = new Priority();
        prior2.setTitle("Zero");
        Priority created = priorityRepo.create(prior);
        Priority created2 = priorityRepo.create(prior2);

        Long id = created.getId();
        assertNotNull(id);

        //get priority by Id test:
        Priority readed = priorityRepo.read(id);
        assertNotNull(readed);

        //Update pririty test
        String update = "Changed";
        created.setTitle("Changed");
        Priority updated = priorityRepo.update(created);
        assertNotNull(updated);
        assertEquals(created.getTitle(), updated.getTitle());


        //Delete priority tests
        priorityRepo.delete(created2.getId());
        readed = priorityRepo.read(created2.getId());
        assertNull(readed);

        //get all pririties

        assertTrue(!priorityRepo.getAll().isEmpty());
    }

}