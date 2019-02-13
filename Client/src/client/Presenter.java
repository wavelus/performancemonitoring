package client;

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
        view.jTextField.setText(databaseAnswer);
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