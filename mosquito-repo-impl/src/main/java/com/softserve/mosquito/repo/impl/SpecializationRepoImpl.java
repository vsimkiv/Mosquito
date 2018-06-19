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
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Long specializationId = (Long) session.save(specialization);
            specialization.setId(specializationId);

            transaction.commit();
        } catch (Exception e) {
            LOGGER.error("Error during save specialization!" + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }finally {
            if(session != null){
                session.close();
            }
        }

        return specialization;
    }

    @Override
    public Specialization read(Long id) {
        Session session = null;
        Transaction transaction = null;
        Specialization specialization = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            specialization = session.get(Specialization.class, id);
        } catch (Exception e) {
            LOGGER.error("Specialization reading was failed!", e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            if(session != null){
                session.close();
            }
        }

        return specialization;
    }

    @Override
    public Specialization update(Specialization specialization) {
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(specialization);
            transaction.commit();
        }catch (Exception e){
            LOGGER.error("Specialization updating was failed" + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }finally {
            if(session != null){
                session.close();
            }
        }

        return specialization;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;

        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Specialization specialization = session.get(Specialization.class, id);
            session.delete(specialization);
            transaction.commit();
        }catch (Exception e){
            LOGGER.error("Specialization deleting was failed" + e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Specialization> getAll() {
        Session session = null;
        List<Specialization> specializations = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("From " + Specialization.class.getName());

            specializations = query.list();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }finally {
            if(session != null){
                session.close();
            }
        }

        return specializations;
    }
}
