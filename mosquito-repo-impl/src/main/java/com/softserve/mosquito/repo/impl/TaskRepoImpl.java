package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
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

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class TaskRepoImpl implements TaskRepo {
    private static final Logger LOGGER = LogManager.getLogger(TaskRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public TaskRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Task create(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            LOGGER.info(task.toString());
            session.save(task);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            LOGGER.error("Error during saving task", e.getMessage());
        }finally {
            session.close();
        }
        return task;
    }

    @Override
    public Task read(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            Task task = session.get(Task.class, id);
            transaction.commit();
            return task;
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Task reading was failed!", e.getMessage());
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public Task update(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.update(task);

            transaction.commit();
            return task;
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Task updating was failed!" + e.getMessage());
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            Task task = session.get(Task.class, id);
            session.delete(task);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Comment deleting was failed!", e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> readAll() {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery("FROM " + Task.class.getName());
        return query.list();
    }
}
