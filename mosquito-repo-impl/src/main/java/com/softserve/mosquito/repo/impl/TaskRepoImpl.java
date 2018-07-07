package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(task);
            return task;
        } catch (HibernateException e) {
            LOGGER.error("Problem with creating task" + Arrays.toString(e.getStackTrace()));
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Task read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(Task.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Problem with reading task by id" + Arrays.toString(e.getStackTrace()));
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Task update(Task task) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(task);
            session.getTransaction().commit();
            return task;
        } catch (HibernateException e) {
            LOGGER.error("Problem with updating task" + Arrays.toString(e.getStackTrace()));
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
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Problem with deleting task" + Arrays.toString(e.getStackTrace()));
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Task> getSubTasks(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery("select t FROM " + Task.class.getName() +
                    " t JOIN t.parentTask p WHERE p.id = :parentId ", Task.class);
            query.setParameter("parentId", id);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting sub tasks" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Task> getAllProjects() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            Query<Task> query = session.createQuery("FROM " + Task.class.getName() +
                    " WHERE parentTask = null ", Task.class);
            List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting projects" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Task> getProjectsByOwner(Long ownerId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery(
                    "FROM " + Task.class.getName() + " t JOIN t.owner o " +
                            "WHERE t.parentTask = null AND o.id = :ownerId", Task.class);
            query.setParameter("ownerId", ownerId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting projects" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Task> getByOwner(Long ownerId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery("FROM " + Task.class.getName() +
                    " t WHERE t.owner.id= :ownerId ", Task.class);
            query.setParameter("ownerId", ownerId);
            return query.getResultList();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting tasks by owner" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Task> getByWorker(Long workerId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery("FROM " + Task.class.getName() +
                    " t JOIN t.worker w WHERE w.id = :workerId ", Task.class);
            query.setParameter("workerId", workerId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting tasks by worker" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        } finally {
            if (session != null) session.close();
        }
    }

    /*
      methods required by trello
     */
    @Transactional
    @Override
    public Task getByName(String name) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery("FROM " + Task.class.getName() +
                    " WHERE name = :taskName ", Task.class);
            query.setParameter("taskName", name);
            return (Task) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error(" Problem with getting task by name" + Arrays.toString(e.getStackTrace()));
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    @Transactional
    public Task getByTrelloId(String trelloId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Task> query = session.createQuery("FROM " + Task.class.getName() +
                    " WHERE trello_id = :trelloId ", Task.class);
            query.setParameter("trelloId", trelloId);
            return query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error with create task" + e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
}
