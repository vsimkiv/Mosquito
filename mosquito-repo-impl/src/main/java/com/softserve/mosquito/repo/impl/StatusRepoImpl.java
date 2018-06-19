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
    public Status create(Status status) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Long statusId = (Long) session.save(status);
            status.setId(statusId);
            return status;
        } catch (HibernateException e) {
            LOGGER.error("Error during save status!" + e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }

    }

    @Override
    public Status read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Status status = session.get(Status.class, id);
            return status;
        } catch (HibernateException e) {
            LOGGER.error("Status reading was failed!", e.getMessage());
        } finally {
            if (session != null) session.close();
        }

        return null;
    }

    @Override
    public Status update(Status status) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(status);
            session.getTransaction().commit();
            return status;
        } catch (HibernateException e) {
            LOGGER.error("Status updating was failed" + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            Status status = session.get(Status.class, id);
            session.delete(status);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Status deleting was failed" + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Status> getAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("From " + Status.class.getName());
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }

    }
}