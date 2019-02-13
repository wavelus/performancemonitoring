package com.wavelus.dao;

import com.wavelus.jappo.model.SingleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SingleLogDao implements SingleLogDaoInterface<SingleLog, Long> {

    private Session currentSession;

    private Transaction currentTransaction;

    public SingleLogDao() {
    }

    private static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration().configure("resources/hibernate.cfg.xml");
        configuration.addAnnotatedClass(SingleLog.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void persist(SingleLog entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(SingleLog entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public SingleLog findById(Long id) {
        return getCurrentSession().get(SingleLog.class, id);
    }

    @Override
    public void delete(SingleLog entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SingleLog> findAll() {
        List<SingleLog> singleLogs = (List<SingleLog>) getCurrentSession().createQuery("from SINGLE_LOG ").list();
        return singleLogs;
    }

    @Override
    public void deleteAll() {
        List<SingleLog> entityList = findAll();
        for (SingleLog entity : entityList) {
            delete(entity);
        }
    }
}
