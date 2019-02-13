package main;

import presenter.ServerThreadPool;

import java.util.*;

public class Main {
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        ServerThreadPool server = new ServerThreadPool(8888);
        new Thread(server).start();

        while (true) {
            System.out.println("\nEnter empty line to quir");
            String message = scanner.nextLine();
            if (message == null || message.isEmpty()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping Server");
        server.stop();

    }
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build();
//        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        SQLQuery query = session.createSQLQuery("explain analyze  select name, joindate, city from users natural join usersaddress");
//        List<Object>rows = query.list();
//        for(Object  row: rows){
//            System.out.println(row.toString());
//        }

//        Float executionTime = Float.valueOf(rows.get(7).toString().replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
//        executionTime= executionTime*1000000;
//        Long exec = (executionTime).longValue();
////            System.out.println(query.getQueryString());
//        System.out.println(exec);
//        session.close();
//    }
}
