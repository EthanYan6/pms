package com.buaa.pms.entity;

public class PmsHuman {

    private String humUid;
    private String humId;
    private String humName;
    private String humOrgUid;
    private String humPosition;
    private String humAbilityType;
    private String humAbilityLevel;
    private String humAbilityDesc;
    private String humPhone;
    private String humEmail;

    public String getHumUid() {
        return humUid;
    }

    public void setHumUid(String humUid) {
        this.humUid = humUid;
    }

    public String getHumId() {
        return humId;
    }

    public void setHumId(String humId) {
        this.humId = humId;
    }

    public String getHumName() {
        return humName;
    }

    public void setHumName(String humName) {
        this.humName = humName;
    }

    public String getHumOrgUid() {
        return humOrgUid;
    }

    public void setHumOrgUid(String humOrgUid) {
        this.humOrgUid = humOrgUid;
    }

    public String getHumPosition() {
        return humPosition;
    }

    public void setHumPosition(String humPosition) {
        this.humPosition = humPosition;
    }

    public String getHumAbilityType() {
        return humAbilityType;
    }

    public void setHumAbilityType(String humAbilityType) {
        this.humAbilityType = humAbilityType;
    }

    public String getHumAbilityLevel() {
        return humAbilityLevel;
    }

    public void setHumAbilityLevel(String humAbilityLevel) {
        this.humAbilityLevel = humAbilityLevel;
    }

    public String getHumAbilityDesc() {
        return humAbilityDesc;
    }

    public void setHumAbilityDesc(String humAbilityDesc) {
        this.humAbilityDesc = humAbilityDesc;
    }

    public String getHumPhone() {
        return humPhone;
    }

    public void setHumPhone(String humPhone) {
        this.humPhone = humPhone;
    }

    public String getHumEmail() {
        return humEmail;
    }

    public void setHumEmail(String humEmail) {
        this.humEmail = humEmail;
    }

    @Override
    public String toString() {
        return "PmsHuman{" +
                "humUid='" + humUid + '\'' +
                ", humId='" + humId + '\'' +
                ", humName='" + humName + '\'' +
                ", humOrgUid='" + humOrgUid + '\'' +
                ", humPosition='" + humPosition + '\'' +
                ", humAbilityType='" + humAbilityType + '\'' +
                ", humAbilityLevel='" + humAbilityLevel + '\'' +
                ", humAbilityDesc='" + humAbilityDesc + '\'' +
                ", humPhone='" + humPhone + '\'' +
                ", humEmail='" + humEmail + '\'' +
                '}';
    }
}
