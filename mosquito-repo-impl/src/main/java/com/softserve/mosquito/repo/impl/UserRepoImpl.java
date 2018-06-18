package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {java.lang.Exception.class})
public class UserRepoImpl implements UserRepo {

    private static final Logger LOGGER = LogManager.getLogger(UserRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public UserRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User create(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Long id = (Long) session.save(user);
            if (id == null)
                throw new HibernateException("Did not get id!");
            return user;
        } catch (HibernateException e) {
            LOGGER.error("Error during save user! " + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(User.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Reading user was failed!" + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User update(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            LOGGER.error("Updating user was failed!" + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Deleting user was failed!" + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> readAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<User> users = session.createQuery("FROM " + User.class.getName());
            return users.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    public User readByEmail(String email) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM " + User.class.getName() + " WHERE email = :email ");
            query.setParameter("email", email);
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Reading user was failed!" + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> readBySpecializationId(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM " + User.class.getName() +
                    " u JOIN u.specializations s WHERE s.id = :id ");
            query.setParameter("id", id);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error("Reading users was failed!" + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
}
