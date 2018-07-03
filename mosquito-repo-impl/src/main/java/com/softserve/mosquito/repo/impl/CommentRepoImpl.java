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
    public Comment create(Comment comment) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(comment);
            LOGGER.info("Comment created successfully " + comment + " for user: " + comment.getAuthor().getId());
        } catch (HibernateException e) {
            LOGGER.info("Error during save comment!");
            LOGGER.error(e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return comment;
    }

    @Override
    public Comment read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(Comment.class, id);
        } catch (HibernateException e) {
            LOGGER.info("Reading comment was failed!");
            LOGGER.error(e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(comment);
            session.getTransaction().commit();
            LOGGER.info("Comment updated successfully " + comment + " for user: " + comment.getAuthor().getId());
            return comment;
        } catch (HibernateException e) {
            LOGGER.info("Updating comment was failed!");
            LOGGER.error(e.getMessage());
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
            Comment comment = session.get(Comment.class, id);
            session.delete(comment);
            session.getTransaction().commit();
            LOGGER.info("Comment deleted successfully " + comment + " for user: " + comment.getAuthor().getId());
        } catch (HibernateException e) {
            LOGGER.info("Deleting comment was failed!");
            LOGGER.error(e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Comment> getByTaskId(Long taskId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("SELECT T.comments FROM " + Task.class.getName() + " T WHERE T.id = " + taskId + "", Comment.class)
                    .getResultList();
        } catch (HibernateException e) {
            LOGGER.info("Retrieving comments for task with id " + taskId + " was failed!");
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }
}