package jappo;

import model.SingleLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;


public class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<SingleLog> logs = new ArrayList<SingleLog>();
            logs = (ArrayList<SingleLog>) objectInputStream.readObject();

//            SingleLog singleLog = (SingleLog)objectInputStream.readObject();
//            System.out.println(singleLog.toString());
            System.out.println(logs.toString());

            ExporterLog exporterLog = new ExporterLog(logs);
            new Thread(exporterLog).run();
            //            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//            // Send a welcome message to the client.
//            out.println("Hello, you are client #" + serverText);
//
//            // Get messages from the client, line by line; return them capitalized
//            while (true) {
//                String input = in.readLine();
//                if (input == null || input.isEmpty()) {
//                    break;
//                }
//                out.println(input.toUpperCase());
//            }
        } catch (IOException e) {
            System.out.println("Error handling client #" + serverText);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println("Cannot cast!");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
            System.out.println("Connection with client # " + serverText + " closed");
        }
    }
}
