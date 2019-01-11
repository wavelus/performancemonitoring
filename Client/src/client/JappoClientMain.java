package client;

import jappo.Jappo;
import model.SingleLog;

public class JappoClientMain {
    public static void main(String[] args) throws Exception {
        SingleLog singleLog = new SingleLog("sdaf", System.nanoTime(), System.nanoTime());
        SingleLog btnLog = new SingleLog();
        btnLog.setLabelOfTestedElement("label");
        btnLog.setTimeOfExecution(System.nanoTime());
        btnLog.setTimeOfResult(System.nanoTime());

        Jappo jappo = new Jappo("localhost",9898);
        jappo.addLog(singleLog);
        jappo.addLog(btnLog);
        jappo.sendLogs();
    }

}
