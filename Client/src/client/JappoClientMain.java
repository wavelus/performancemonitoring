package client;

import jappo.Jappo;
import model.SingleLog;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class JappoClientMain {
    public static void main(String[] args) throws Exception {
//        System.out.println("Enter the IP address of a machine running the capitalize server:");
//        String serverAddress = new Scanner(System.in).nextLine();
//        Socket socket = new Socket(serverAddress, 9898);
//
//        // Streams for conversing with server
////        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        OutputStream outputStream = socket.getOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        SingleLog singleLog = new SingleLog("sdaf", new Date(), new Date());
//        objectOutputStream.writeObject(singleLog);
//
//        socket.close();

        Jappo jappo = new Jappo("localhost",9898);
        jappo.addLog(singleLog);
        jappo.sendLogs();
    }

}
