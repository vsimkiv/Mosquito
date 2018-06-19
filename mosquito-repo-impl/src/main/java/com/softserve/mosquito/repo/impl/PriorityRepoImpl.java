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
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Long priorityId = (Long) session.save(priority);
            transaction.commit();
            if (priorityId == null)
            priority.setId(priorityId);
        } catch (Exception e) {
            LOGGER.error("Error during save priority!" + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }finally {
            if(session != null){
                session.close();
            }
        }

        return priority;
    }

    @Override
    public Priority read(Long id) {
        Session session = null;
        Priority priority = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            priority = session.get(Priority.class, id);
        } catch (Exception e) {
            LOGGER.error("Priority reading was failed!", e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }

        return priority;
    }

    @Override
    public Priority update(Priority priority) {
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(priority);
            transaction.commit();
        }catch (Exception e){
            LOGGER.error("Priority updating was failed" + e.getMessage());
            if (transaction != null){
                transaction.rollback();
            }
            return null;
        } finally {
            if(session != null){
                session.close();
            }
        }
        return priority;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        Priority priority = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            priority = session.get(Priority.class, id);
            session.delete(priority);
            transaction.commit();
        }catch (Exception e){
            LOGGER.error("Priority deleting was failed" + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Priority> getAll() {
        Session session = null;
        Transaction transaction = null;
        List<Priority> priorities = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("From " + Priority.class.getName());

            priorities = query.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            if(session != null){
                session.close();
            }
        }
        return priorities;
    }
}