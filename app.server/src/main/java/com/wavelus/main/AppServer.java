package com.wavelus.main;

import com.wavelus.presenter.ServerThreadPool;

import java.util.Scanner;

public class AppServer {
    public static void main(String[] args) {

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
}
