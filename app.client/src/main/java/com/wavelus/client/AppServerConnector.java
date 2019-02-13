package com.wavelus.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppServerConnector {
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    public AppServerConnector(String serverAddress, Integer serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getDataFromDatabase(String request) {
        String answer = "";
        output.println(request);
        try {
            answer = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }


}
