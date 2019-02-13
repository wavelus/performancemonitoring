package jappo;

import jappo.model.SingleLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;


public class WorkerRunnable implements Runnable{
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }
    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ArrayList<SingleLog> logs = new ArrayList<SingleLog>();
            logs = (ArrayList<SingleLog>)objectInputStream.readObject();

            ExporterLog exporterLog= new ExporterLog(logs);
            new Thread(exporterLog).run();

        } catch (IOException e) {
            System.out.println("Error handling client #" + serverText);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (ClassCastException e){
            System.out.println("Cannot cast!");
        }finally {
            try { clientSocket.close(); } catch (IOException e) {}
            System.out.println("Connection with client # " + serverText + " closed");
        }
    }
}
