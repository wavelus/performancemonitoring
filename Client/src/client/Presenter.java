package client;

import jappo.Jappo;
import jappo.JappoBuilder;
import jappo.model.SingleLog;

import java.io.IOException;

public class Presenter {
    private AppServerConnector appServerConnector;
    private View view;

    public Presenter(View view){
        this.view = view;
    }

    public void action(String string){

        if(string.equals("String One")){
            updateNumberOfRecordsAction();
        }

        if(string.equals("String Two")){
            updateNameAction();
        }

    }


    public void updateView(String databaseAnswer){
        SingleLog updatingViewLog = new SingleLog();
        updatingViewLog.setLabelOfTestedElement("Updating View");
        updatingViewLog.setTimeOfExecution(System.nanoTime());
        view.jTextField.setText(databaseAnswer);
        updatingViewLog.setTimeOfResult(System.nanoTime());

        Jappo jappo = new JappoBuilder().setServerAddress("localhost").setServerPort(9898).createJappo();
        jappo.addLog(updatingViewLog);
        jappo.sendLogs();
    }
    public void updateNumberOfRecordsAction(){
        String result = "";
        try {

            appServerConnector = new AppServerConnector("localhost", 8888);

        } catch (IOException e) {
            e.printStackTrace();
        }
        result = appServerConnector.getDataFromDatabase("numberOfUsers");
        updateView(result);
    }

    public void updateNameAction(){
        String result = "";
        try {
            appServerConnector = new AppServerConnector("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = appServerConnector.getDataFromDatabase("maxUser");
        updateView(result);

    }





}