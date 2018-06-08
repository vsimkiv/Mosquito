package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        try(Session session = sessionFactory.openSession()) {
            session.save(trelloInfo);

            return trelloInfo;
        } catch (HibernateException e) {
            LOGGER.error("Error during save trelloInfo!");
        }
        return null;
    }

    @Override
    public TrelloInfo read(Long id) {
        try (Session session = sessionFactory.openSession()) {
           return session.get(TrelloInfo.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Error during read trelloInfo!");
        }
        return null;
    }

    @Override
    public TrelloInfo update(TrelloInfo trelloInfo) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(trelloInfo);
            session.getTransaction().commit();
            return trelloInfo;
        } catch (HibernateException e) {
            LOGGER.error("Error during update trelloInfo!");
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            TrelloInfo trelloInfo = session.get(TrelloInfo.class, id);
            session.delete(trelloInfo);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Error during delete trelloInfo!");
        }
    }

    @Override
    public List<TrelloInfo> readAll() {
        Query<TrelloInfo> query = sessionFactory.getCurrentSession().createQuery("FROM" + TrelloInfo.class.getName());
        return query.list();
    }
}
