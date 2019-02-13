package presenter;

import org.hibernate.SQLQuery;


import java.util.List;

public class DatabaseConnector {
    private DatabaseReference databaseReference = DatabaseReference.getInstance();

    public String getDataAsStringFromDatabase(String preparedQuery){
        String result="";
        SQLQuery query = databaseReference.session.createSQLQuery(preparedQuery);
        List<Object> list = query.list();
        for (Object row: list) {
            result+=row.toString()+"\n";
        }
        Long executionTime = getTimeExecution(preparedQuery);
        System.out.println(executionTime);
        return result;
    }

    public List getDataAsListOfStringFromDatabase(String preparedQuery){

        SQLQuery query = databaseReference.session.createSQLQuery(preparedQuery);
        Long executionTime = getTimeExecution(preparedQuery);
        System.out.println(executionTime);
        return query.list();
    }

    private Long getTimeExecution(String preparedQuery){
        String convertedQuery = convertSQLQueryToAnalyzeSQLQuery(preparedQuery);
        SQLQuery analyzedQuery = databaseReference.session.createSQLQuery(convertedQuery);
        String rowWithExecutionTime = getRowWithExecutionTime(analyzedQuery.list());
        Long executionTime = getExecutionTimeFromRow(rowWithExecutionTime);
        return executionTime;
    }

    private String convertSQLQueryToAnalyzeSQLQuery(String query){
        return "explain analyze " + query;
    }
    private String getRowWithExecutionTime(List<String> rows){
        String result = "";
        for (String row: rows) {
            if(row.matches(".*Execution Time.*")){
                result = row;
            }
        }
        return result;
    }

    private Long getExecutionTimeFromRow(String row){
        Float executionTimeInMS = Float.valueOf(row.replaceAll("[^\\d.]+|\\.(?!\\d)", ""))*1000000;
        Long executionTimeInNS = executionTimeInMS.longValue();
        return executionTimeInNS;
    }

    public String prepareQuery(String clientRequest){
        String preparedQuery="";
        if(clientRequest.equals("numberOfUsers")){
            preparedQuery = "select count(*) from users";
        }else if(clientRequest.equals("maxUser")){
            preparedQuery = "select max(name) from users";
        }
        return preparedQuery;
    }

}
