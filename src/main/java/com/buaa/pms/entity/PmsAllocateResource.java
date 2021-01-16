package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsAllocateResource {

    private String arUid;
    private String arResUid;
    private String arProjUid;
    private String arProcUid;
    private String arTaskUid;
    private String arResPlanUid;
    private String arResReqUid;
    private Timestamp arResStartDateTime;
    private Timestamp arResFinishDateTime;
    private Float arResAmount;
    private Float arResWork;
    private Integer arResWorkModel;
    private Integer arIsEffective;

    public String getArUid() {
        return arUid;
    }

    public void setArUid(String arUid) {
        this.arUid = arUid;
    }

    public String getArResUid() {
        return arResUid;
    }

    public void setArResUid(String arResUid) {
        this.arResUid = arResUid;
    }

    public String getArProjUid() {
        return arProjUid;
    }

    public void setArProjUid(String arProjUid) {
        this.arProjUid = arProjUid;
    }

    public String getArProcUid() {
        return arProcUid;
    }

    public void setArProcUid(String arProcUid) {
        this.arProcUid = arProcUid;
    }

    public String getArTaskUid() {
        return arTaskUid;
    }

    public void setArTaskUid(String arTaskUid) {
        this.arTaskUid = arTaskUid;
    }

    public String getArResPlanUid() {
        return arResPlanUid;
    }

    public void setArResPlanUid(String arResPlanUid) {
        this.arResPlanUid = arResPlanUid;
    }

    public String getArResReqUid() {
        return arResReqUid;
    }

    public void setArResReqUid(String arResReqUid) {
        this.arResReqUid = arResReqUid;
    }

    public Timestamp getArResStartDateTime() {
        return arResStartDateTime;
    }

    public void setArResStartDateTime(Timestamp arResStartDateTime) {
        this.arResStartDateTime = arResStartDateTime;
    }

    public Timestamp getArResFinishDateTime() {
        return arResFinishDateTime;
    }

    public void setArResFinishDateTime(Timestamp arResFinishDateTime) {
        this.arResFinishDateTime = arResFinishDateTime;
    }

    public Float getArResAmount() {
        return arResAmount;
    }

    public void setArResAmount(Float arResAmount) {
        this.arResAmount = arResAmount;
    }

    public Float getArResWork() {
        return arResWork;
    }

    public void setArResWork(Float arResWork) {
        this.arResWork = arResWork;
    }

    public Integer getArResWorkModel() {
        return arResWorkModel;
    }

    public void setArResWorkModel(Integer arResWorkModel) {
        this.arResWorkModel = arResWorkModel;
    }

    public Integer getArIsEffective() {
        return arIsEffective;
    }

    public void setArIsEffective(Integer arIsEffective) {
        this.arIsEffective = arIsEffective;
    }
}
