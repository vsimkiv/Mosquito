package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
public class TaskRepoImplTest {

    @Autowired
    TaskRepoImpl userRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void CRUDTest(){
        Task task = new Task();

    }

}