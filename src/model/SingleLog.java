package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="SINGLE_LOG")
public class SingleLog {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    private String labelOfTestedElement;
    private Date timeOfExecution;
    private Date timeOfResult;

    public SingleLog(){}

    public SingleLog(String label, Date timeOfExecution, Date timeOfResult){
        this.labelOfTestedElement = label;
        this.timeOfExecution = timeOfExecution;
        this.timeOfResult = timeOfResult;
    }



    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(Date timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public Date getTimeOfResult() {
        return timeOfResult;
    }

    public void setTimeOfResult(Date timeOfResult) {
        this.timeOfResult = timeOfResult;
    }

    public String getLabelOfTestedElement() {
        return labelOfTestedElement;
    }

    public void setLabelOfTestedElement(String labelOfTestedElement) {
        this.labelOfTestedElement = labelOfTestedElement;
    }



}
