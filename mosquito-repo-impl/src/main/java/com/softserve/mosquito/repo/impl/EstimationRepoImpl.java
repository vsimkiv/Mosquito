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
@Repository
public class EstimationRepoImpl implements EstimationRepo {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public EstimationRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Estimation create(Estimation estimation) {
        try (Session session = sessionFactory.openSession()) {
            session.save(estimation);
        } catch (HibernateException e) {
            LOGGER.error("Error during save estimation!");
        }
        return estimation;
    }

    @Override
    public Estimation read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Estimation.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Reading estimation was failed!");
        }
        return null;
    }

    @Override
    public Estimation update(Estimation estimation) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(estimation);
            session.getTransaction().commit();
            return estimation;
        } catch (HibernateException e) {
            LOGGER.error("Updating estimation was failed!");
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Estimation estimation = session.get(Estimation.class, id);
            session.delete(estimation);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Deleting comment was failed!");
        }
    }


    }

