package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
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
public class TrelloInfoRepoImp implements TrelloInfoRepo {

    private static final Logger LOGGER = LogManager.getLogger(TrelloInfoRepoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    public TrelloInfoRepoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TrelloInfo create(TrelloInfo trelloInfo) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(trelloInfo);
            return trelloInfo;
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public TrelloInfo read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(TrelloInfo.class, id);
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public TrelloInfo update(TrelloInfo trelloInfo) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(trelloInfo);
            session.getTransaction().commit();
            return trelloInfo;
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            TrelloInfo trelloInfo = session.get(TrelloInfo.class, id);
            session.delete(trelloInfo);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<TrelloInfo> getAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<TrelloInfo> trelloInfos = session.createQuery("FROM " + TrelloInfo.class.getName());
            return trelloInfos.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
}
