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

    @Transactional
    @Override
    public Task create(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.save(task);
            return task;
        } catch (HibernateException e) {
            LOGGER.error("Problem with creating task" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Transactional
    @Override
    public Task read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Problem with reading task by id" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Transactional
    @Override
    public Task update(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.update(task);
            return task;
        } catch (HibernateException e) {
            LOGGER.error("Problem with updating task" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Problem with deleting task" + Arrays.toString(e.getStackTrace()));
        }
    }

    @Transactional
    @Override
    public List<Task> getSubTasks(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE parent_id = :parentId ");
            query.setParameter("parentId", id);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting sub tasks" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public List<Task> getAllProjects() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE parent_id = null ");
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting projects" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public List<Task> getProjectsByOwner(Long ownerId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "FROM " + Task.class.getName() + " WHERE parent_id = null AND owner_id = ownerId");
            query.setParameter("ownerId", ownerId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting projects" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        }
    }

    @Override
    public List<Task> getByOwner(Long ownerId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE parent_id = :ownerId ");
            query.setParameter("ownerId", ownerId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting tasks by owner" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        }
    }

    @Override
    public List<Task> getByWorker(Long workerId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE parent_id = :workerId ");
            query.setParameter("workerId", workerId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Problem with getting tasks by worker" + Arrays.toString(e.getStackTrace()));
            return new ArrayList<>();
        }
    }

    /*
      methods required by trello
     */
    @Transactional
    @Override
    public Task getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE name = :taskName ");
            query.setParameter("taskName", name);
            return (Task) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error(" Problem with getting task by name" + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    @Transactional
    public Task getByTrelloId(String trelloId) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM " + Task.class.getName() + " WHERE trello_id = :trelloId ");
            query.setParameter("trelloId", trelloId);
            return (Task) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error with create task" + e.getMessage());
            return null;
        }
    }
}
