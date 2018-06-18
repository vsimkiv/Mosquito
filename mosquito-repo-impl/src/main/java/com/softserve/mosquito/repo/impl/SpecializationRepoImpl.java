package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Specialization create(Specialization specialization) {
        try (Session session = sessionFactory.openSession()) {
            Long specializationId = (Long) session.save(specialization);
            specialization.setId(specializationId);
        } catch (Exception e) {
            LOGGER.error("Error during save specialization!" + e.getMessage());
        }
        return specialization;
    }

    @Override
    public Specialization read(Long id) {
        try {
            Session session = sessionFactory.openSession();
            Specialization specialization = session.get(Specialization.class, id);
            return specialization;
        } catch (Exception e) {
            LOGGER.error("Specialization reading was failed!", e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization update(Specialization specialization) {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(specialization);
            transaction.commit();
            return specialization;
        }catch (Exception e){
            LOGGER.error("Specialization updating was failed" + e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {

        try{
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Specialization specialization = session.get(Specialization.class, id);
            session.delete(specialization);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error("Specialization deleting was failed" + e.getMessage());
        }
    }

    @Override
    public List<Specialization> getAll() {
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("From " + Specialization.class.getName());

            return query.list();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
