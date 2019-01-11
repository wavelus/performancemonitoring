package main;

import model.SingleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

public class HibernateTest {

    public static void main (String [] args){

        SingleLog singleLog = new SingleLog("apud", new Date(),new Date());

        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build()) {
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(singleLog);
            session.getTransaction().commit();
            session.close();

        }catch (Exception e) {e.printStackTrace();}
    }
}
