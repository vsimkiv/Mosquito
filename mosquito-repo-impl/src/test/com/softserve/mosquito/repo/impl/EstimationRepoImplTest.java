package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Estimation;
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
public class EstimationRepoImplTest {
    @Autowired
   EstimationRepoImpl estimationRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void GeneralTest() {
        //Create estimation test
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(100);
        estimation.setRemaining(100);

        Estimation created = estimationRepo.create(estimation);
        Long id = created.getId();
        assertNotNull(id);

        //get estimation by id:
        Estimation readed = estimationRepo.read(id);
        assertNotNull(readed);

        //update estimation:
        created.setTimeEstimation(200);
        created.setRemaining(200);
        Estimation updated = estimationRepo.update(created);
        assertEquals(created.getId(),updated.getId());

        //Delete estimation:
        estimationRepo.delete(updated.getId());
        Estimation deleted = estimationRepo.read(updated.getId());
        assertNull(deleted);

    }

}