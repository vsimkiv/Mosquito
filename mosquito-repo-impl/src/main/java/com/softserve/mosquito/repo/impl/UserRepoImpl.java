package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @Transactional
    public User create(User user) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(user);
        if (id == null) {
            LOGGER.error("Error during save user!");
            return null;
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    @Transactional
    public User update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> users = session.createQuery("FROM " + User.class.getName(), User.class);
        return users.list();
    }

    @Override
    @Transactional(readOnly = true)
    public User readByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM " + User.class.getName() +
                " WHERE email = :email ", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> readBySpecializationId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM " + User.class.getName() +
                " u JOIN u.specializations s WHERE s.id = :id ", User.class);
        query.setParameter("id", id);
        return query.list();
    }

    @Override
    public boolean isConfirmed(String email) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.createQuery("FROM " + User.class.getName() + " WHERE email = :email",
                User.class).setParameter("email",email).getSingleResult();
        return user.isConfirmed();
    }
}
