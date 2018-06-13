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
        try{
            Session session = sessionFactory.getCurrentSession();
            Long specializationId = (Long) session.save(specialization);
            specialization.setId(specializationId);

            return specialization;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization read(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            //TODO: change id type from Byte to Long
            Specialization specialization = (Specialization) session.get(Specialization.class, Long.valueOf(id.toString()));

            return specialization;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization update(Specialization specialization) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(specialization);

            return specialization;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            //TODO: change from delete(Long) to delete(Specialization) and change id type from Byte to Long
            Specialization specialization = new Specialization();
            specialization.setId(Long.valueOf(id.toString()));

            Session session = sessionFactory.getCurrentSession();

            session.delete(specialization);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Specialization> getAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("From " + Specialization.class.getName());

            return query.list();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
