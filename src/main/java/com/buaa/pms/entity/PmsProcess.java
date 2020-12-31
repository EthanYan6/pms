package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsProcess {

    private String procUid;
    private String procId;
    private String procName;
    private String procProjUid;
    private String procAuthor;
    private String procDescription;
    private Timestamp procPlanStartDateTime;
    private Timestamp procPlanFinishDateTime;
    private Integer procPlanDur;
    private Integer procState;

    public String getProcUid() {
        return procUid;
    }

    public void setProcUid(String procUid) {
        this.procUid = procUid;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getProcProjUid() {
        return procProjUid;
    }

    public void setProcProjUid(String procProjUid) {
        this.procProjUid = procProjUid;
    }

    public String getProcAuthor() {
        return procAuthor;
    }

    public void setProcAuthor(String procAuthor) {
        this.procAuthor = procAuthor;
    }

    public String getProcDescription() {
        return procDescription;
    }

    public void setProcDescription(String procDescription) {
        this.procDescription = procDescription;
    }

    public Timestamp getProcPlanStartDateTime() {
        return procPlanStartDateTime;
    }

    public void setProcPlanStartDateTime(Timestamp procPlanStartDateTime) {
        this.procPlanStartDateTime = procPlanStartDateTime;
    }

    public Timestamp getProcPlanFinishDateTime() {
        return procPlanFinishDateTime;
    }

    public void setProcPlanFinishDateTime(Timestamp procPlanFinishDateTime) {
        this.procPlanFinishDateTime = procPlanFinishDateTime;
    }

    public Integer getProcPlanDur() {
        return procPlanDur;
    }

    public void setProcPlanDur(Integer procPlanDur) {
        this.procPlanDur = procPlanDur;
    }

    public Integer getProcState() {
        return procState;
    }

    public void setProcState(Integer procState) {
        this.procState = procState;
    }
}
