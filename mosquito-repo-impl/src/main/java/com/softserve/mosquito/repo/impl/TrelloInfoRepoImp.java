package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TrelloInfoRepoImp implements TrelloInfoRepo {

    private static final Logger LOGGER = LogManager.getLogger(TrelloInfoRepoImp.class);
    private SessionFactory sessionFactory;

    @Autowired
    public TrelloInfoRepoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public TrelloInfo create(TrelloInfo trelloInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.save(trelloInfo);
        return trelloInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public TrelloInfo read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TrelloInfo.class, id);
    }

    @Override
    @Transactional
    public TrelloInfo update(TrelloInfo trelloInfo) {
        Session session = sessionFactory.getCurrentSession();
        session.update(trelloInfo);
        return trelloInfo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TrelloInfo trelloInfo = session.get(TrelloInfo.class, id);
        session.delete(trelloInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrelloInfo> getAll() {
        Session session = sessionFactory.getCurrentSession();

        Query<TrelloInfo> trelloInfos = session.createQuery("FROM " +
                TrelloInfo.class.getName(), TrelloInfo.class);
        return trelloInfos.list();
    }
}
