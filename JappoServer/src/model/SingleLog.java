package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="SINGLE_LOG")
public class SingleLog implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;

    private String labelOfTestedElement;
    private Long timeOfExecution;
    private Long timeOfResult;
    private String uniqueProgramExecutionId;

    public SingleLog(){}

    public SingleLog(String label){
        this.labelOfTestedElement = label;
    }
    public SingleLog(String label, Long timeOfExecution, Long timeOfResult){
        this.labelOfTestedElement = label;
        this.timeOfExecution = timeOfExecution;
        this.timeOfResult = timeOfResult;
    }

    @Override
    public String toString() {
        return (labelOfTestedElement + " "+timeOfExecution+" "+timeOfResult);
    }

    public String getUniqueProgramExecutionId() {return uniqueProgramExecutionId;    }

    public void setUniqueProgramExecutionId(String uniqueProgramExecutionId) {
        this.uniqueProgramExecutionId = uniqueProgramExecutionId;}

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(Long timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public Long getTimeOfResult() {
        return timeOfResult;
    }

    public void setTimeOfResult(Long timeOfResult) {
        this.timeOfResult = timeOfResult;
    }

    public String getLabelOfTestedElement() {
        return labelOfTestedElement;
    }

    public void setLabelOfTestedElement(String labelOfTestedElement) {
        this.labelOfTestedElement = labelOfTestedElement;
    }
}
