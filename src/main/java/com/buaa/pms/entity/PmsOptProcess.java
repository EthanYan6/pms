package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsOptProcess {

    private String optProcUid;
    private String optProcOptProjUid;
    private String optProcProjUid;
    private String optProcProcUid;
    private Timestamp optProcPlanStartDateTime;
    private Timestamp optProcPlanFinishiDateTime;
    private Integer optProcPlanDur;

    public String getOptProcUid() {
        return optProcUid;
    }

    public void setOptProcUid(String optProcUid) {
        this.optProcUid = optProcUid;
    }

    public String getOptProcOptProjUid() {
        return optProcOptProjUid;
    }

    public void setOptProcOptProjUid(String optProcOptProjUid) {
        this.optProcOptProjUid = optProcOptProjUid;
    }

    public String getOptProcProjUid() {
        return optProcProjUid;
    }

    public void setOptProcProjUid(String optProcProjUid) {
        this.optProcProjUid = optProcProjUid;
    }

    public String getOptProcProcUid() {
        return optProcProcUid;
    }

    public void setOptProcProcUid(String optProcProcUid) {
        this.optProcProcUid = optProcProcUid;
    }

    public Timestamp getOptProcPlanStartDateTime() {
        return optProcPlanStartDateTime;
    }

    public void setOptProcPlanStartDateTime(Timestamp optProcPlanStartDateTime) {
        this.optProcPlanStartDateTime = optProcPlanStartDateTime;
    }

    public Timestamp getOptProcPlanFinishiDateTime() {
        return optProcPlanFinishiDateTime;
    }

    public void setOptProcPlanFinishiDateTime(Timestamp optProcPlanFinishiDateTime) {
        this.optProcPlanFinishiDateTime = optProcPlanFinishiDateTime;
    }

    public Integer getOptProcPlanDur() {
        return optProcPlanDur;
    }

    public void setOptProcPlanDur(Integer optProcPlanDur) {
        this.optProcPlanDur = optProcPlanDur;
    }
}
