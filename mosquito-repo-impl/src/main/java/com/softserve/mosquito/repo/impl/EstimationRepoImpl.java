package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.repo.api.EstimationRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstimationRepoImpl implements EstimationRepo {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public EstimationRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Estimation create(Estimation estimation) {
        Session session = sessionFactory.getCurrentSession();
        session.save(estimation);
        return estimation;
    }

    @Override
    @Transactional(readOnly = true)
    public Estimation read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Estimation.class, id);
    }

    @Override
    @Transactional
    public Estimation update(Estimation estimation) {
        Session session = sessionFactory.getCurrentSession();
        session.update(estimation);
        return estimation;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Estimation estimation = session.get(Estimation.class, id);
        session.delete(estimation);
    }
}

