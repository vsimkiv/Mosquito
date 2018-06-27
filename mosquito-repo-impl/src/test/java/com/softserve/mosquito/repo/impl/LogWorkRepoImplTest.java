package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.DataSource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LogWorkRepoImplTest {
    @Autowired
    LogWorkRepoImpl logWorkRepo;

    @Autowired
    javax.sql.DataSource dataSource;

    @Test
    public void GeneralTest(){
        //Create logwork:
        LogWork logWork = new LogWork();
        logWork.setDescription("GoodJob");
        logWork.setLogged(20);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        logWork.setAuthor(user);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(100);
        estimation.setRemaining(100);
        logWork.setEstimation(estimation);
        LogWork created = logWorkRepo.create(logWork);
        Long id = created.getId();
        assertNotNull(id);

        //get logwork by id
        LogWork readed = logWorkRepo.read(id);
        assertNotNull(readed);
        // update logwork
        created.setLogged(50);
        created.setDescription("BadJob");
        LogWork updated = logWorkRepo.update(created);
        assertNotNull(updated);
        assertEquals(created.getLogged(), updated.getLogged());


        //get logWork by user id
      List byUserId = logWorkRepo.getByUserId(user.getId());
        assertEquals(1, byUserId.size());

        //get logWork by estimation id
        List byEstimationId = logWorkRepo.getByEstimationId(estimation.getId());
        assertEquals(1L, byEstimationId.size() );

        //get all logWorks
        assertTrue(!logWorkRepo.readAll().isEmpty());

        //delete logWork
        logWorkRepo.delete(created.getId());
        LogWork deleted = logWorkRepo.read(created.getId());
        assertNull(deleted);



    }


}