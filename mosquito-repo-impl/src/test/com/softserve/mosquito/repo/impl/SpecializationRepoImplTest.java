package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
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
public class SpecializationRepoImplTest {

    @Autowired
    SpecializationRepoImpl specializationRepo;

    @Autowired
    DataSource dataSource;


    @Test
    public void GeneralTest(){
        //Create Specialization test:
        Specialization spec = new Specialization();
        spec.setTitle("Boss");
        Specialization spec2 = new Specialization();
        spec2.setTitle("CEO");
        Specialization created = specializationRepo.create(spec);
        Specialization created2 = specializationRepo.create(spec2);

        Long id = created.getId();
        assertNotNull(id);

        //get specialization by Id test:
        Specialization readed = specializationRepo.read(id);
        assertNotNull(readed);

        //Update specialization test
        created.setTitle("BIG");
        Specialization updated = specializationRepo.update(created);
        assertNotNull(updated);
        assertEquals(created.getTitle(), updated.getTitle());

        //Delete specializations tests
        specializationRepo.delete(created.getId());
        readed = specializationRepo.read(created.getId());
       assertNull(readed);

        //get all specializations

        assertTrue(!specializationRepo.getAll().isEmpty());
    }

}