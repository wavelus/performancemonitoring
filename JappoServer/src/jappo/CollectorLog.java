package jappo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CollectorLog implements Runnable {
    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
            Executors.newFixedThreadPool(10);

    public CollectorLog(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            this.threadPool.execute(
                    new WorkerRunnable(clientSocket,
                            clientSocket.toString()));
        }
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}

//public class CollectorLog extends Thread{
//    private Socket socket;
//    private int clientNumber;
//
//    public CollectorLog(Socket socket, int clientNumber) {
//        this.socket = socket;
//        this.clientNumber = clientNumber;
//        System.out.println("New client #" + clientNumber + " connected at " + socket);
//    }
//
//    public void run() {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//            // Send a welcome message to the client.
//            out.println("Hello, you are client #" + clientNumber);
//
//            // Get messages from the client, line by line; return them capitalized
//            while (true) {
//                String input = in.readLine();
//                if (input == null || input.isEmpty()) {
//                    break;
//                }
//                out.println(input.toUpperCase());
//            }
//        } catch (IOException e) {
//            System.out.println("Error handling client #" + clientNumber);
//        } finally {
//            try { socket.close(); } catch (IOException e) {}
//            System.out.println("Connection with client # " + clientNumber + " closed");
//        }
//    }
//}
