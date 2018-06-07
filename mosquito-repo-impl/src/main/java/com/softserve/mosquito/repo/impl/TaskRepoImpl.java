package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
        Session session = sessionFactory.getCurrentSession();
        session.save(task);
        return task;
    }

    @Transactional
    @Override
    public Task read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    @Transactional
    @Override
    public Task update(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        return task;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        session.delete(task);
    }

    @Override
    public List<Task> readAll() {
        Query<Task> query = sessionFactory.getCurrentSession().createQuery("FROM " + Task.class.getName());
        return query.list();
    }
}
