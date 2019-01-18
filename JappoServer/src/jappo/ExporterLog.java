package jappo;

import model.SingleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.ArrayList;
import java.util.UUID;

public class ExporterLog implements Runnable {
    ArrayList<SingleLog> logs;
    Session session = null;

    public ExporterLog(ArrayList<SingleLog> logs){
        this.logs = logs;
    }

    private void addToSession(){
        String uniqueId = UUID.randomUUID().toString();
        for (SingleLog log:logs) {
            log.setUniqueProgramExecutionId(uniqueId);
            session.save(log);
        }
    }
    @Override
    public void run() {
        try (
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build()) {
                Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
                SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                session = sessionFactory.openSession();
                session.beginTransaction();
                addToSession();
                session.getTransaction().commit();
                session.close();

        }catch (Exception e) {e.printStackTrace();}
    }
}
