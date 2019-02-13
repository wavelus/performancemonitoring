package com.wavelus.main;

import com.wavelus.jappo.CollectorLog;

import java.util.Scanner;

public class JappoServer {

    public static void main(String[] args) {
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
