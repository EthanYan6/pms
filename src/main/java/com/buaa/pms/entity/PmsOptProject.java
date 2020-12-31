package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsOptProject {

    private String optProjUid;
    private String optProjName;
    private Timestamp optProjStartDateTime;
    private Timestamp optProjFinishDateTime;
    private Integer optProjApply;
    private Timestamp optProjCreate;
    private Timestamp optProjApplyTime;

    public String getOptProjUid() {
        return optProjUid;
    }

    public void setOptProjUid(String optProjUid) {
        this.optProjUid = optProjUid;
    }

    public String getOptProjName() {
        return optProjName;
    }

    public void setOptProjName(String optProjName) {
        this.optProjName = optProjName;
    }

    public Timestamp getOptProjStartDateTime() {
        return optProjStartDateTime;
    }

    public void setOptProjStartDateTime(Timestamp optProjStartDateTime) {
        this.optProjStartDateTime = optProjStartDateTime;
    }

    public Timestamp getOptProjFinishDateTime() {
        return optProjFinishDateTime;
    }

    public void setOptProjFinishDateTime(Timestamp optProjFinishDateTime) {
        this.optProjFinishDateTime = optProjFinishDateTime;
    }

    public Integer getOptProjApply() {
        return optProjApply;
    }

    public void setOptProjApply(Integer optProjApply) {
        this.optProjApply = optProjApply;
    }

    public Timestamp getOptProjCreate() {
        return optProjCreate;
    }

    public void setOptProjCreate(Timestamp optProjCreate) {
        this.optProjCreate = optProjCreate;
    }

    public Timestamp getOptProjApplyTime() {
        return optProjApplyTime;
    }

    public void setOptProjApplyTime(Timestamp optProjApplyTime) {
        this.optProjApplyTime = optProjApplyTime;
    }
}
