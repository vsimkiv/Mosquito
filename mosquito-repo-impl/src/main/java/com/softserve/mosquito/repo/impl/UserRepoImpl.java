package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.sql.JoinType;
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
        try (Session session = sessionFactory.openSession()) {
            Long id = (Long) session.save(user);
            if (id == null) {
                throw new HibernateException("Did not get id!");
            }
        } catch (HibernateException e) {
            LOGGER.error("Error during save user! " + e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public User read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Reading user was failed!" + e.getMessage());
            return null;
        }
    }

    @Override
    public User update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            LOGGER.error("Updating user was failed!" + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Deleting user was failed!" + e.getMessage());
        }
    }

    @Override
    public List<User> readAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> users = session.createQuery("FROM " + User.class.getName());
            return users.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public User readByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            return (User) criteria.list().stream().findFirst().orElse(null);
        } catch (HibernateException e) {
            LOGGER.error("Reading user was failed!" + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> readBySpecializationId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.createAlias("specializations", "s");
            criteria.add(Restrictions.eq("s.id", id));
            return criteria.list();
        } catch (HibernateException e) {
            LOGGER.error("Reading users was failed!" + e.getMessage());
            return null;
        }
    }
}