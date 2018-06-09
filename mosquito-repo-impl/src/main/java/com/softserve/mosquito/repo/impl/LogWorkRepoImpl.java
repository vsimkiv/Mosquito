package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class LogWorkRepoImpl implements LogWorkRepo {

    private static final Logger LOGGER = LogManager.getLogger(LogWorkRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public LogWorkRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LogWorkRepoImpl() { }

    @Transactional
    @Override
    public LogWork create(LogWork logWork) {
        try (Session session = sessionFactory.openSession()) {
            session.save(logWork);
        } catch (HibernateException e) {
            LOGGER.error("Error during save logWork!");
        }
        return logWork;
    }

    @Override
    public LogWork read(Long logWorkid) {
        Session session = sessionFactory.openSession();
        LogWork logWork = session.load(LogWork.class, logWorkid);
        return logWork;
    }

    @Override
    public LogWork update(LogWork logWork) {
        Session session = sessionFactory.openSession();
        session.update(logWork);
        session.getTransaction().commit();
        return logWork;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        LogWork logWork = new LogWork();
        logWork.setId(id);
        session.delete(logWork);
        session.getTransaction().commit();
    }

    @Override
    public List<LogWork> readAll() {
        return null;
    }

    public List<LogWork> getLogWorksByUser(Long userId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(LogWork.class);
        List<LogWork> logWorks = criteria.add(Restrictions.eq("author_id", userId)).list();
        return logWorks;
    }

}
