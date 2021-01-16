package com.buaa.pms.entity;

public class PmsTaskResPlan {

    private String resPlanUid;
    private String resPlanId;
    private String resPlanProjUid;
    private String resPlanProcUid;
    private String resPlanTaskUid;
    private Integer resPlanPriority;

    public String getResPlanUid() {
        return resPlanUid;
    }

    public void setResPlanUid(String resPlanUid) {
        this.resPlanUid = resPlanUid;
    }

    public String getResPlanId() {
        return resPlanId;
    }

    public void setResPlanId(String resPlanId) {
        this.resPlanId = resPlanId;
    }

    public String getResPlanProjUid() {
        return resPlanProjUid;
    }

    public void setResPlanProjUid(String resPlanProjUid) {
        this.resPlanProjUid = resPlanProjUid;
    }

    public String getResPlanProcUid() {
        return resPlanProcUid;
    }

    public void setResPlanProcUid(String resPlanProcUid) {
        this.resPlanProcUid = resPlanProcUid;
    }

    public String getResPlanTaskUid() {
        return resPlanTaskUid;
    }

    public void setResPlanTaskUid(String resPlanTaskUid) {
        this.resPlanTaskUid = resPlanTaskUid;
    }

    public Integer getResPlanPriority() {
        return resPlanPriority;
    }

    public void setResPlanPriority(Integer resPlanPriority) {
        this.resPlanPriority = resPlanPriority;
    }

    @Override
    public String toString() {
        return "PmsTaskResPlan{" +
                "resPlanUid='" + resPlanUid + '\'' +
                ", resPlanId='" + resPlanId + '\'' +
                ", resPlanProjUid='" + resPlanProjUid + '\'' +
                ", resPlanProcUid='" + resPlanProcUid + '\'' +
                ", resPlanTaskUid='" + resPlanTaskUid + '\'' +
                ", resPlanPriority=" + resPlanPriority +
                '}';
    }
}
