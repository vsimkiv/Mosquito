package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.CommentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class CommentRepoImpl implements CommentRepo {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CommentRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Comment create(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
        return comment;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Comment.class, id);
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comment);
        return comment;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        session.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByTaskId(Long taskId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT T.comments FROM " + Task.class.getName() + " T WHERE T.id = " + taskId + "")
                .getResultList();
    }
}