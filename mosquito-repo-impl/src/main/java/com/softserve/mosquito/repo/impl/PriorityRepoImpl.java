package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.sql.ResultSet;
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
    public Priority create(Priority priority) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Byte priorityId = (Byte) session.save(priority);
            priority.setId(priorityId);

            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority read(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            //TODO: change id type from Byte to Long
            Priority priority = (Priority) session.get(Priority.class, Byte.valueOf(id.toString()));

            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority update(Priority priority) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(priority);

            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            //TODO: change from delete(Long) to delete(Specialization) and change id type from Byte to Long
            Priority priority = new Priority();
            priority.setId(Byte.valueOf(id.toString()));

            Session session = sessionFactory.getCurrentSession();
            session.delete(priority);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Priority> readAll() {
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("From " + Priority.class.getName());

            return query.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}