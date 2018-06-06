package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            session.save(comment);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Error during save comment!");
        } finally {
            session.close();
        }

        return comment;
    }

    @Override
    public Comment read(Long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            Comment comment = session.get(Comment.class, id);

            transaction.commit();
            return comment;
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Reading comment was failed!");
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public Comment update(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.update(comment);

            transaction.commit();
            return comment;
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Updating comment was failed!");
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
            Comment comment = session.get(Comment.class, id);
            session.delete(comment);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            LOGGER.error("Deleting comment was failed!");
        } finally {
            session.close();
        }
    }

    @Override
    public List<Comment> readAll() {
        Query<Comment> query = sessionFactory.getCurrentSession().createQuery("FROM " + Comment.class.getName());
        return query.list();
    }
}
