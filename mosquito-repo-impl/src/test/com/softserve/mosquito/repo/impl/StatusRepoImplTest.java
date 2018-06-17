package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StatusRepoImplTest {

    @Autowired
    StatusRepoImpl statusRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void GeneralTest() {
        //Create Status test:
        Status st = new Status();
        st.setTitle("Cancelled");
        Status st2 = new Status();
        st2.setTitle("ForYesterday");
        Status created = statusRepo.create(st);
        Status created2 = statusRepo.create(st2);

        Long id = created.getId();
        assertNotNull(id);

        //get status by Id test:
        Status readed = statusRepo.read(id);
        assertNotNull(readed);

        //Update status test

        created.setTitle("Changed");
        Status updated = statusRepo.update(created);
        assertEquals(created.getTitle(), updated.getTitle());


        //Delete status tests
        statusRepo.delete(updated.getId());
        readed = statusRepo.read(updated.getId());
        assertNull(readed);

        //get all statuses

        assertTrue(!statusRepo.getAll().isEmpty());
    }
}