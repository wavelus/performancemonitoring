package main;

import jappo.CollectorLog;
import model.SingleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;

public class JappoServerMain {

    public static void main (String [] args) {
//      ThreadPooledServer
        Scanner scanner = new Scanner(System.in);
        CollectorLog server = new CollectorLog(9898);
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
}
