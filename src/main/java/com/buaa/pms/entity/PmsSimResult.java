package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsSimResult {

    private String simRsUid;
    private String simRsSimProjUid;
    private Float simRsDurU;
    private Float simRsSigma;
    private Float simRsProba1;
    private Float simRsProba2;
    private Float simRsProba3;
    private Integer simRsCount;
    private Timestamp simRsDateTime;

    public String getSimRsUid() {
        return simRsUid;
    }

    public void setSimRsUid(String simRsUid) {
        this.simRsUid = simRsUid;
    }

    public String getSimRsSimProjUid() {
        return simRsSimProjUid;
    }

    public void setSimRsSimProjUid(String simRsSimProjUid) {
        this.simRsSimProjUid = simRsSimProjUid;
    }

    public Float getSimRsDurU() {
        return simRsDurU;
    }

    public void setSimRsDurU(Float simRsDurU) {
        this.simRsDurU = simRsDurU;
    }

    public Float getSimRsSigma() {
        return simRsSigma;
    }

    public void setSimRsSigma(Float simRsSigma) {
        this.simRsSigma = simRsSigma;
    }

    public Float getSimRsProba1() {
        return simRsProba1;
    }

    public void setSimRsProba1(Float simRsProba1) {
        this.simRsProba1 = simRsProba1;
    }

    public Float getSimRsProba2() {
        return simRsProba2;
    }

    public void setSimRsProba2(Float simRsProba2) {
        this.simRsProba2 = simRsProba2;
    }

    public Float getSimRsProba3() {
        return simRsProba3;
    }

    public void setSimRsProba3(Float simRsProba3) {
        this.simRsProba3 = simRsProba3;
    }

    public Integer getSimRsCount() {
        return simRsCount;
    }

    public void setSimRsCount(Integer simRsCount) {
        this.simRsCount = simRsCount;
    }

    public Timestamp getSimRsDateTime() {
        return simRsDateTime;
    }

    public void setSimRsDateTime(Timestamp simRsDateTime) {
        this.simRsDateTime = simRsDateTime;
    }
}
