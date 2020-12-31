package com.buaa.pms.model;

import com.buaa.pms.entity.PmsAllocateResource;
import com.buaa.pms.entity.PmsTaskResReq;

import java.sql.Timestamp;

public class ResOcpyNode {

    private PmsAllocateResource pmsAllocateResource;
    private PmsTaskResReq pmsTaskResReq;
    private String projName;
    private String taskName;
    private String resUid;
    private String resName;
    private Integer resType;
    private Timestamp resStartDateTime;
    private Timestamp resFinishDateTime;
    private Float resAmount;
    private Float resWork;
    private Integer resWorkModel;

    public ResOcpyNode preOcpy;
    public ResOcpyNode sucOcpy;

    public ResOcpyNode() {}

    public ResOcpyNode(PmsTaskResReq pmsTaskResReq) {
        this.pmsTaskResReq = pmsTaskResReq;
        this.resUid = pmsTaskResReq.getResReqResUid();
        this.resType = pmsTaskResReq.getResReqResType();
        this.resAmount = pmsTaskResReq.getResReqResAmount();
        this.resWork = pmsTaskResReq.getResReqResWork();
        this.resWorkModel = pmsTaskResReq.getResReqResWorkModel();
    }

    public ResOcpyNode(PmsAllocateResource pmsAllocateResource) {
        this.pmsAllocateResource = pmsAllocateResource;
        this.resStartDateTime = pmsAllocateResource.getArResStartDateTime();
        this.resFinishDateTime = pmsAllocateResource.getArResStartDateTime();
        this.resUid = pmsAllocateResource.getArResUid();
        this.resAmount = pmsAllocateResource.getArResAmount();
        this.resWork = pmsAllocateResource.getArResWork();
        this.resWorkModel = pmsAllocateResource.getArResWorkModel();
    }

    public ResOcpyNode(ResOcpyNode resOcpyNode) {
        this.pmsAllocateResource = resOcpyNode.pmsAllocateResource;
        this.pmsTaskResReq = resOcpyNode.pmsTaskResReq;
        this.resUid = resOcpyNode.resUid;
        this.resName = resOcpyNode.resName;
        this.resType = resOcpyNode.resType;
        this.resStartDateTime = resOcpyNode.resStartDateTime;
        this.resFinishDateTime = resOcpyNode.resFinishDateTime;
        this.resAmount = resOcpyNode.resAmount;
        this.resWork = resOcpyNode.resWork;
        this.resWorkModel = resOcpyNode.resWorkModel;
    }

    public ResOcpyNode(Timestamp resStart, Timestamp resFinish) {
        this.resStartDateTime = resStart;
        this.resFinishDateTime = resFinish;
    }

    public PmsAllocateResource getPmsAllocateResource() {
        return pmsAllocateResource;
    }

    public void setPmsAllocateResource(PmsAllocateResource pmsAllocateResource) {
        this.pmsAllocateResource = pmsAllocateResource;
    }

    public PmsTaskResReq getPmsTaskResReq() {
        return pmsTaskResReq;
    }

    public void setPmsTaskResReq(PmsTaskResReq pmsTaskResReq) {
        this.pmsTaskResReq = pmsTaskResReq;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResUid() {
        return resUid;
    }

    public void setResUid(String resUid) {
        this.resUid = resUid;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Timestamp getResStartDateTime() {
        return resStartDateTime;
    }

    public void setResStartDateTime(Timestamp resStartDateTime) {
        this.resStartDateTime = resStartDateTime;
    }

    public Timestamp getResFinishDateTime() {
        return resFinishDateTime;
    }

    public void setResFinishDateTime(Timestamp resFinishDateTime) {
        this.resFinishDateTime = resFinishDateTime;
    }

    public Float getResAmount() {
        return resAmount;
    }

    public void setResAmount(Float resAmount) {
        this.resAmount = resAmount;
    }

    public Float getResWork() {
        return resWork;
    }

    public void setResWork(Float resWork) {
        this.resWork = resWork;
    }

    public Integer getResWorkModel() {
        return resWorkModel;
    }

    public void setResWorkModel(Integer resWorkModel) {
        this.resWorkModel = resWorkModel;
    }

    public ResOcpyNode getPreOcpy() {
        return preOcpy;
    }

    public void setPreOcpy(ResOcpyNode preOcpy) {
        this.preOcpy = preOcpy;
    }

    public ResOcpyNode getSucOcpy() {
        return sucOcpy;
    }

    public void setSucOcpy(ResOcpyNode sucOcpy) {
        this.sucOcpy = sucOcpy;
    }
}
