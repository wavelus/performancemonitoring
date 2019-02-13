package com.wavelus.jappo;

import com.wavelus.jappo.model.SingleLog;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Jappo implements Runnable {
    private String serverAddress;
    private Integer serverPort;
    private ArrayList<SingleLog> logs;

    public Jappo(String serverAddress, Integer serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.logs = new ArrayList<SingleLog>();
    }

    public void addLog(SingleLog log) {
        logs.add(log);
    }

    public void sendLogs() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(serverAddress, serverPort);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(logs);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
