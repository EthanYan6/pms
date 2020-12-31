package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsOptTask {

    private String optTaskUid;
    private String optTaskOptProjUid;
    private String optTaskOptProcUid;
    private String optTaskTaskUid;
    private String optTaskProjUid;
    private String optTaskProcUid;
    private Timestamp optTaskPlanStartDateTime;
    private Timestamp optTaskPlanFinishDateTime;
    private Integer optTaskPlanDur;

    public String getOptTaskUid() {
        return optTaskUid;
    }

    public void setOptTaskUid(String optTaskUid) {
        this.optTaskUid = optTaskUid;
    }

    public String getOptTaskOptProjUid() {
        return optTaskOptProjUid;
    }

    public void setOptTaskOptProjUid(String optTaskOptProjUid) {
        this.optTaskOptProjUid = optTaskOptProjUid;
    }

    public String getOptTaskOptProcUid() {
        return optTaskOptProcUid;
    }

    public void setOptTaskOptProcUid(String optTaskOptProcUid) {
        this.optTaskOptProcUid = optTaskOptProcUid;
    }

    public String getOptTaskTaskUid() {
        return optTaskTaskUid;
    }

    public void setOptTaskTaskUid(String optTaskTaskUid) {
        this.optTaskTaskUid = optTaskTaskUid;
    }

    public String getOptTaskProjUid() {
        return optTaskProjUid;
    }

    public void setOptTaskProjUid(String optTaskProjUid) {
        this.optTaskProjUid = optTaskProjUid;
    }

    public String getOptTaskProcUid() {
        return optTaskProcUid;
    }

    public void setOptTaskProcUid(String optTaskProcUid) {
        this.optTaskProcUid = optTaskProcUid;
    }

    public Timestamp getOptTaskPlanStartDateTime() {
        return optTaskPlanStartDateTime;
    }

    public void setOptTaskPlanStartDateTime(Timestamp optTaskPlanStartDateTime) {
        this.optTaskPlanStartDateTime = optTaskPlanStartDateTime;
    }

    public Timestamp getOptTaskPlanFinishDateTime() {
        return optTaskPlanFinishDateTime;
    }

    public void setOptTaskPlanFinishDateTime(Timestamp optTaskPlanFinishDateTime) {
        this.optTaskPlanFinishDateTime = optTaskPlanFinishDateTime;
    }

    public Integer getOptTaskPlanDur() {
        return optTaskPlanDur;
    }

    public void setOptTaskPlanDur(Integer optTaskPlanDur) {
        this.optTaskPlanDur = optTaskPlanDur;
    }
}
