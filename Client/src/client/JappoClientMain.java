package client;

import jappo.Jappo;
import jappo.JappoBuilder;
import model.SingleLog;

import java.io.*;
import java.net.Socket;

public class JappoClientMain {
    public static void main(String[] args) throws Exception {

        System.out.println(getDataFromDatabaseOrSomethingLikeThat("localhost", 9889));

        //        SingleLog singleLog = new SingleLog("sdaf", System.nanoTime(), System.nanoTime());
//        SingleLog btnLog = new SingleLog();
//        btnLog.setLabelOfTestedElement("label");
//        btnLog.setTimeOfExecution(System.nanoTime());
//        btnLog.setTimeOfResult(System.nanoTime());
//
//        Jappo jappo = new JappoBuilder().setServerAddress("localhost").setServerPort(9898).createJappo();
//        jappo.addLog(singleLog);
//        jappo.addLog(btnLog);
//        jappo.sendLogs();
    }


    public static String getDataFromDatabaseOrSomethingLikeThat(String serverAddress, Integer serverPort) throws IOException {
        String answer = "";
        Socket socket = new Socket(serverAddress, serverPort);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream,true);
        out.println("asf");

        while(in.readLine()!=null){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            answer = answer + in.readLine();
        }
//        System.out.print(answer);
        return answer;
    }

}
