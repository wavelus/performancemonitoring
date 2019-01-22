package main;

import model.Event;
import model.User;
import model.UsersAddress;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        User user1 = new User();
        User user2 = new User();
        UsersAddress usersAddress1 = new UsersAddress();
        UsersAddress usersAddress2 = new UsersAddress();
        Set<Event> events = new HashSet<>();
        events.add(new Event("event1"));
        events.add(new Event("event2"));

        user1.setName("Adam1");
        user2.setName("Adam2");
        user1.setJoinDate(new Date());
        user2.setJoinDate(new Date());
        usersAddress1.setCity("NY1");
        usersAddress2.setCity("NY2");
        usersAddress1.setStreet("Krakoska1");
        usersAddress2.setStreet("Krakoska2");
        usersAddress1.setPostalCode("123331");
        usersAddress2.setPostalCode("123332");
        usersAddress1.setStreetNumber(1231);
        usersAddress2.setStreetNumber(1232);

        user1.setUsersAddress(usersAddress1);
        user2.setUsersAddress(usersAddress2);
        user1.setEvents(events);
        user2.setEvents(events);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        session.save(user1);
//        session.save(user2);
//        session.save(usersAddress1);
//        session.save(usersAddress2);
//        session.getTransaction().commit();
//        Query query = session.getNamedQuery("User.byName");
        SQLQuery query = session.createSQLQuery("select name, joindate, city from users natural join usersaddress");
        List<Object[]> rows = query.list();
        for (Object[] row : rows) {

        }
        session.close();

        System.out.println("gsgdfs");


    }
}
