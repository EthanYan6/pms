package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsSimProject {

    private String simProjUid;
    private String simProjName;
    private String simProjProjUid;
    private String simProjProcUid;
    private Timestamp simProjEarlyStartDateTime;
    private Timestamp simProjLatefinishDateTime;
    private Timestamp simProjStartDateTime;
    private Timestamp simProjFinishDateTime;
    private Integer simProjDur;
    private Integer simProjMode;
    private Timestamp simProjCreate;

    public String getSimProjUid() {
        return simProjUid;
    }

    public void setSimProjUid(String simProjUid) {
        this.simProjUid = simProjUid;
    }

    public String getSimProjName() {
        return simProjName;
    }

    public void setSimProjName(String simProjName) {
        this.simProjName = simProjName;
    }

    public String getSimProjProjUid() {
        return simProjProjUid;
    }

    public void setSimProjProjUid(String simProjProjUid) {
        this.simProjProjUid = simProjProjUid;
    }

    public String getSimProjProcUid() {
        return simProjProcUid;
    }

    public void setSimProjProcUid(String simProjProcUid) {
        this.simProjProcUid = simProjProcUid;
    }

    public Timestamp getSimProjEarlyStartDateTime() {
        return simProjEarlyStartDateTime;
    }

    public void setSimProjEarlyStartDateTime(Timestamp simProjEarlyStartDateTime) {
        this.simProjEarlyStartDateTime = simProjEarlyStartDateTime;
    }

    public Timestamp getSimProjLatefinishDateTime() {
        return simProjLatefinishDateTime;
    }

    public void setSimProjLatefinishDateTime(Timestamp simProjLatefinishDateTime) {
        this.simProjLatefinishDateTime = simProjLatefinishDateTime;
    }

    public Timestamp getSimProjStartDateTime() {
        return simProjStartDateTime;
    }

    public void setSimProjStartDateTime(Timestamp simProjStartDateTime) {
        this.simProjStartDateTime = simProjStartDateTime;
    }

    public Timestamp getSimProjFinishDateTime() {
        return simProjFinishDateTime;
    }

    public void setSimProjFinishDateTime(Timestamp simProjFinishDateTime) {
        this.simProjFinishDateTime = simProjFinishDateTime;
    }

    public Integer getSimProjDur() {
        return simProjDur;
    }

    public void setSimProjDur(Integer simProjDur) {
        this.simProjDur = simProjDur;
    }

    public Integer getSimProjMode() {
        return simProjMode;
    }

    public void setSimProjMode(Integer simProjMode) {
        this.simProjMode = simProjMode;
    }

    public Timestamp getSimProjCreate() {
        return simProjCreate;
    }

    public void setSimProjCreate(Timestamp simProjCreate) {
        this.simProjCreate = simProjCreate;
    }
}
