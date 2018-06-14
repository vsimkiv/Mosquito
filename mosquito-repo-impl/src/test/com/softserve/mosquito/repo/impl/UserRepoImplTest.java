package com.softserve.mosquito.repo.impl;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.softserve.mosquito.entities.User;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepoImplTest {


    SessionFactory sessionFactory;

    UserRepoImpl userRepo;

    @Before
    public void setUp() throws Exception {
        AnnotationConfiguration configuration = new AnnotationConfiguration();

        configuration.addAnnotatedClass(SuperHero.class)

                .addAnnotatedClass(SuperPower.class)

                .addAnnotatedClass(SuperPowerType.class);

        configuration.setProperty("hibernate.dialect",

                "org.hibernate.dialect.H2Dialect");

        configuration.setProperty("hibernate.connection.driver_class",

                "org.h2.Driver");

        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");

        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        sessionFactory = configuration.buildSessionFactory();
        userRepo = new UserRepoImpl(sessionFactory);
    }

    @Test
    public void create() throws Exception {
    }

    @Test
    public void read() throws Exception {
        User users = userRepo.read(42L);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

}