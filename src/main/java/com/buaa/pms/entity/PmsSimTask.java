package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsSimTask {

    private String simTaskUid;
    private String simTaskSimProjUid;
    private String simTaskTaskUid;
    private String simTaskProjUid;
    private String simTaskProcUid;
    private Timestamp simTaskPlanStartDateTime;
    private Timestamp simTaskPlanFinishDateTime;
    private Integer simTaskPlanDur;
    private Timestamp simTaskEarlyStartDateTime;
    private Timestamp simTaskLateFinishDateTime;
    private Float simTaskWait;
    private Float simTaskDurU;
    private Float simTaskDurSigma;
    private Float simTaskPctRework;

    public String getSimTaskUid() {
        return simTaskUid;
    }

    public void setSimTaskUid(String simTaskUid) {
        this.simTaskUid = simTaskUid;
    }

    public String getSimTaskSimProjUid() {
        return simTaskSimProjUid;
    }

    public void setSimTaskSimProjUid(String simTaskSimProjUid) {
        this.simTaskSimProjUid = simTaskSimProjUid;
    }

    public String getSimTaskTaskUid() {
        return simTaskTaskUid;
    }

    public void setSimTaskTaskUid(String simTaskTaskUid) {
        this.simTaskTaskUid = simTaskTaskUid;
    }

    public String getSimTaskProjUid() {
        return simTaskProjUid;
    }

    public void setSimTaskProjUid(String simTaskProjUid) {
        this.simTaskProjUid = simTaskProjUid;
    }

    public String getSimTaskProcUid() {
        return simTaskProcUid;
    }

    public void setSimTaskProcUid(String simTaskProcUid) {
        this.simTaskProcUid = simTaskProcUid;
    }

    public Timestamp getSimTaskPlanStartDateTime() {
        return simTaskPlanStartDateTime;
    }

    public void setSimTaskPlanStartDateTime(Timestamp simTaskPlanStartDateTime) {
        this.simTaskPlanStartDateTime = simTaskPlanStartDateTime;
    }

    public Timestamp getSimTaskPlanFinishDateTime() {
        return simTaskPlanFinishDateTime;
    }

    public void setSimTaskPlanFinishDateTime(Timestamp simTaskPlanFinishDateTime) {
        this.simTaskPlanFinishDateTime = simTaskPlanFinishDateTime;
    }

    public Integer getSimTaskPlanDur() {
        return simTaskPlanDur;
    }

    public void setSimTaskPlanDur(Integer simTaskPlanDur) {
        this.simTaskPlanDur = simTaskPlanDur;
    }

    public Timestamp getSimTaskEarlyStartDateTime() {
        return simTaskEarlyStartDateTime;
    }

    public void setSimTaskEarlyStartDateTime(Timestamp simTaskEarlyStartDateTime) {
        this.simTaskEarlyStartDateTime = simTaskEarlyStartDateTime;
    }

    public Timestamp getSimTaskLateFinishDateTime() {
        return simTaskLateFinishDateTime;
    }

    public void setSimTaskLateFinishDateTime(Timestamp simTaskLateFinishDateTime) {
        this.simTaskLateFinishDateTime = simTaskLateFinishDateTime;
    }

    public Float getSimTaskWait() {
        return simTaskWait;
    }

    public void setSimTaskWait(Float simTaskWait) {
        this.simTaskWait = simTaskWait;
    }

    public Float getSimTaskDurU() {
        return simTaskDurU;
    }

    public void setSimTaskDurU(Float simTaskDurU) {
        this.simTaskDurU = simTaskDurU;
    }

    public Float getSimTaskDurSigma() {
        return simTaskDurSigma;
    }

    public void setSimTaskDurSigma(Float simTaskDurSigma) {
        this.simTaskDurSigma = simTaskDurSigma;
    }

    public Float getSimTaskPctRework() {
        return simTaskPctRework;
    }

    public void setSimTaskPctRework(Float simTaskPctRework) {
        this.simTaskPctRework = simTaskPctRework;
    }
}
