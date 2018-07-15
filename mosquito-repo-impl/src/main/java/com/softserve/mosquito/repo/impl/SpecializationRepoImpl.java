package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
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
public class SpecializationRepoImpl implements SpecializationRepo {
    private static final Logger LOGGER = LogManager.getLogger(SpecializationRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public SpecializationRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public Specialization create(Specialization specialization) {
        Session session = sessionFactory.getCurrentSession();
        Long specializationId = (Long) session.save(specialization);
        specialization.setId(specializationId);
        return specialization;
    }

    @Override
    @Transactional(readOnly = true)
    public Specialization read(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Specialization.class, id);
    }

    @Override
    @Transactional
    public Specialization update(Specialization specialization) {
        Session session = sessionFactory.getCurrentSession();
        session.update(specialization);
        return specialization;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Specialization specialization = session.get(Specialization.class, id);
        session.delete(specialization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Specialization> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Specialization> query = session.createQuery("From " +
                Specialization.class.getName(), Specialization.class);
        return query.list();
    }
}
