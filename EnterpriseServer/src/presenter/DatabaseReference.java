package presenter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class DatabaseReference{
    public Session session;
    private volatile static DatabaseReference instance;

    private DatabaseReference() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();

    }

    public static DatabaseReference getInstance(){
        if (instance == null){
            synchronized (DatabaseReference.class){
                if (instance == null){
                    instance = new DatabaseReference();
                }
            }
        }
        return instance;
    }

}