package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
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
public class CommentRepoImpl implements CommentRepo {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public CommentRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comment create(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.save(comment);
        } catch (HibernateException e) {
            LOGGER.error("Error during save comment!");
        }
        return comment;
    }

    @Override
    public Comment read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Comment.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Reading comment was failed!");
        }
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(comment);
            session.getTransaction().commit();
            return comment;
        } catch (HibernateException e) {
            LOGGER.error("Updating comment was failed!");
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Comment comment = session.get(Comment.class, id);
            session.delete(comment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Deleting comment was failed!");
        }
    }

    @Override
    public List<Comment> readAll() {
        Query<Comment> query = sessionFactory.getCurrentSession().createQuery("FROM " + Comment.class.getName());
        return query.list();
    }
}
