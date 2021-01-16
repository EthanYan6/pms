package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsTaskResReq {

    private String resReqUid;
    private String resReqId;
    private String resReqResPlanUid;
    private String resReqTaskUid;
    private String resReqProjUid;
    private String resReqProcUid;
    private Integer resReqResType;
    private String resReqResUid;
    private Timestamp resReqResStartDateTime;
    private Timestamp resReqResFinishDateTime;
    private Float resReqResWork;
    private Integer resReqResWorkModel;
    private Float resReqResAmount;

    public String getResReqUid() {
        return resReqUid;
    }

    public void setResReqUid(String resReqUid) {
        this.resReqUid = resReqUid;
    }

    public String getResReqId() {
        return resReqId;
    }

    public void setResReqId(String resReqId) {
        this.resReqId = resReqId;
    }

    public String getResReqResPlanUid() {
        return resReqResPlanUid;
    }

    public void setResReqResPlanUid(String resReqResPlanUid) {
        this.resReqResPlanUid = resReqResPlanUid;
    }

    public String getResReqTaskUid() {
        return resReqTaskUid;
    }

    public void setResReqTaskUid(String resReqTaskUid) {
        this.resReqTaskUid = resReqTaskUid;
    }

    public String getResReqProjUid() {
        return resReqProjUid;
    }

    public void setResReqProjUid(String resReqProjUid) {
        this.resReqProjUid = resReqProjUid;
    }

    public String getResReqProcUid() {
        return resReqProcUid;
    }

    public void setResReqProcUid(String resReqProcUid) {
        this.resReqProcUid = resReqProcUid;
    }

    public Integer getResReqResType() {
        return resReqResType;
    }

    public void setResReqResType(Integer resReqResType) {
        this.resReqResType = resReqResType;
    }

    public String getResReqResUid() {
        return resReqResUid;
    }

    public void setResReqResUid(String resReqResUid) {
        this.resReqResUid = resReqResUid;
    }

    public Timestamp getResReqResStartDateTime() {
        return resReqResStartDateTime;
    }

    public void setResReqResStartDateTime(Timestamp resReqResStartDateTime) {
        this.resReqResStartDateTime = resReqResStartDateTime;
    }

    public Timestamp getResReqResFinishDateTime() {
        return resReqResFinishDateTime;
    }

    public void setResReqResFinishDateTime(Timestamp resReqResFinishDateTime) {
        this.resReqResFinishDateTime = resReqResFinishDateTime;
    }

    public Float getResReqResWork() {
        return resReqResWork;
    }

    public void setResReqResWork(Float resReqResWork) {
        this.resReqResWork = resReqResWork;
    }

    public Integer getResReqResWorkModel() {
        return resReqResWorkModel;
    }

    public void setResReqResWorkModel(Integer resReqResWorkModel) {
        this.resReqResWorkModel = resReqResWorkModel;
    }

    public Float getResReqResAmount() {
        return resReqResAmount;
    }

    public void setResReqResAmount(Float resReqResAmount) {
        this.resReqResAmount = resReqResAmount;
    }
}
