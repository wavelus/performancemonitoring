package main;

import jappo.CollectorLog;

import java.util.Scanner;

public class JappoServerMain {

    public static void main(String[] args) {
//        SingleLogService singleLogService = new SingleLogService();
//        SingleLog singleLog1 = new SingleLog("s1", System.nanoTime(), System.nanoTime(), "u1");
//        SingleLog singleLog2 = new SingleLog("s2", System.nanoTime(), System.nanoTime(), "u1");
//        SingleLog singleLog3 = new SingleLog("s3", System.nanoTime(), System.nanoTime(), "u1");
//
//        singleLogService.persist(singleLog1);
//        singleLogService.persist(singleLog2);
//        singleLogService.persist(singleLog3);
//
//        List<SingleLog> logs = singleLogService.findAll();
//        for(SingleLog s: logs){
//            System.out.println(s.toString());
//        }
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
