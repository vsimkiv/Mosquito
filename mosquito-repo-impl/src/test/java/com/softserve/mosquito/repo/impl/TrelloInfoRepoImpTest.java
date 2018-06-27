package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.entities.User;
import org.junit.Ignore;
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
public class TrelloInfoRepoImpTest {
    @Autowired
    TrelloInfoRepoImp trelloInfoRepoImp;

    @Autowired
    DataSource dataSource;

    @Test
    public void GeneralTest(){
        //create TrelloInfo
        TrelloInfo trelloInfo = new TrelloInfo();
        trelloInfo.setUserTrelloName("BestTrelloUser");
        trelloInfo.setUserTrelloKey("123456789qwertyuiop");
        trelloInfo.setUserTrelloToken("987654321zxcvbnm");
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        trelloInfo.setUser(user);
        TrelloInfo created = trelloInfoRepoImp.create(trelloInfo);
        Long id = created.getId();
        assertNotNull(id);

        //get trelloInfo by id
        TrelloInfo readed = trelloInfoRepoImp.read(id);
        assertNotNull(readed);

        //get all trelloInfos
        assertTrue(!trelloInfoRepoImp.getAll().isEmpty());

        //delete trelloOnfo
        trelloInfoRepoImp.delete(created.getId());
        TrelloInfo deleted = trelloInfoRepoImp.read(created.getId());
        assertNull(deleted);
    }
}