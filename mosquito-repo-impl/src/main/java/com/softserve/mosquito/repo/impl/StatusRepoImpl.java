package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.repo.api.StatusRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StatusRepoImpl implements StatusRepo {

    private static final Logger LOGGER = LogManager.getLogger(StatusRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public StatusRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Status create(Status status) {
        Session session = sessionFactory.getCurrentSession();
        Long statusId = (Long) session.save(status);
        status.setId(statusId);
        return status;
    }

    @Override
    @Transactional(readOnly = true)
    public Status read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Status status = session.get(Status.class, id);
        return status;
    }

    @Override
    @Transactional
    public Status update(Status status) {
        Session session = sessionFactory.getCurrentSession();
        session.update(status);
        return status;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Status status = session.get(Status.class, id);
        session.delete(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Status> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Status> query = session.createQuery("From " + Status.class.getName(), Status.class);
        return query.list();
    }
}