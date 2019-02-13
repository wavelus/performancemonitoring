package presenter;

import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable {
    protected Socket clientSocket = null;
    protected String clientNumber   = null;
    protected DatabaseConnector databaseConnector;
    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.clientNumber   = serverText;
        this.databaseConnector = new DatabaseConnector();
    }

    @Override
    public void run() {
        try {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            OutputStream outstream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outstream,true);

            String clientRequest = in.readLine().trim();
            String preparedQuery = databaseConnector.prepareQuery(clientRequest);
            String result = databaseConnector.getDataAsStringFromDatabase(preparedQuery);

            out.println(result);

        } catch (IOException e) {
            System.out.println("Error handling client #" + clientNumber);
        }catch (ClassCastException e){
            System.out.println("Cannot cast!");
        }finally {
            try { clientSocket.close(); } catch (IOException e) {}
            System.out.println("Connection with client # " + clientNumber + " closed");
        }
    }
}
