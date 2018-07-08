package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PriorityRepoImpl implements PriorityRepo {
    private static final Logger LOGGER = LogManager.getLogger(PriorityRepoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public PriorityRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Priority create(Priority priority) {
        Session session = sessionFactory.getCurrentSession();
        Long priorityId = (Long) session.save(priority);
        if (priorityId != null)
            priority.setId(priorityId);
        return priority;
    }

    @Override
    @Transactional(readOnly = true)
    public Priority read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Priority.class, id);
    }

    @Override
    @Transactional
    public Priority update(Priority priority) {
        Session session = sessionFactory.getCurrentSession();
        session.update(priority);
        return priority;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Priority priority = session.get(Priority.class, id);
        session.delete(priority);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Priority> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Priority> query = session.createQuery("From " +
                Priority.class.getName(), Priority.class);
        return query.list();
    }
}