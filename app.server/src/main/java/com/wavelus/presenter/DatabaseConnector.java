package com.wavelus.presenter;

import com.wavelus.jappo.Jappo;
import com.wavelus.jappo.JappoBuilder;
import com.wavelus.jappo.model.SingleLog;
import org.hibernate.SQLQuery;

import java.util.List;

public class DatabaseConnector {
    private DatabaseReference databaseReference = DatabaseReference.getInstance();

    public String getDataAsStringFromDatabase(String preparedQuery) {
        SingleLog databaseExecutionLog = new SingleLog("databaseExecutionLog");
        String result = "";
        SQLQuery query = databaseReference.session.createSQLQuery(preparedQuery);
        List<Object> list = query.list();
        for (Object row : list) {
            result += row.toString() + "\n";
        }
        databaseExecutionLog.setTimeOfExecution((long) 0);
        Long executionTime = getTimeExecution(preparedQuery);
        databaseExecutionLog.setTimeOfResult(executionTime);

        Jappo jappo = new JappoBuilder().setServerAddress("localhost").setServerPort(9898).createJappo();
        jappo.addLog(databaseExecutionLog);
        jappo.sendLogs();
        return result;
    }

    public List getDataAsListOfStringFromDatabase(String preparedQuery) {

        SQLQuery query = databaseReference.session.createSQLQuery(preparedQuery);
        Long executionTime = getTimeExecution(preparedQuery);
        System.out.println(executionTime);
        return query.list();
    }

    private Long getTimeExecution(String preparedQuery) {
        String convertedQuery = convertSQLQueryToAnalyzeSQLQuery(preparedQuery);
        SQLQuery analyzedQuery = databaseReference.session.createSQLQuery(convertedQuery);
        String rowWithExecutionTime = getRowWithExecutionTime(analyzedQuery.list());
        Long executionTime = getExecutionTimeFromRow(rowWithExecutionTime);
        return executionTime;
    }

    private String convertSQLQueryToAnalyzeSQLQuery(String query) {
        return "explain analyze " + query;
    }

    private String getRowWithExecutionTime(List<String> rows) {
        String result = "";
        for (String row : rows) {
            if (row.matches(".*Execution Time.*")) {
                result = row;
            }
        }
        return result;
    }

    private Long getExecutionTimeFromRow(String row) {
        return Float.valueOf(row.replaceAll("[^\\d.]+|\\.(?!\\d)", "")).longValue() * 1000000;
    }

    public String prepareQuery(String clientRequest) {
        String preparedQuery = "";
        if (clientRequest.equals("numberOfUsers")) {
            preparedQuery = "select count(*) from users";
        } else if (clientRequest.equals("maxUser")) {
            preparedQuery = "select max(name) from users";
        }
        return preparedQuery;
    }

}
