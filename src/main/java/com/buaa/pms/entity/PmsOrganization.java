package com.buaa.pms.entity;

public class PmsOrganization {

    private String orgUid;
    private String orgId;
    private String orgName;
    private String orgParUid;
    private String orgManager;
    private String orgPhone;
    private String orgEmail;
    private String orgAddress;
    private String orgDescription;

    public String getOrgUid() {
        return orgUid;
    }

    public void setOrgUid(String orgUid) {
        this.orgUid = orgUid;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgParUid() {
        return orgParUid;
    }

    public void setOrgParUid(String orgParUid) {
        this.orgParUid = orgParUid;
    }

    public String getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(String orgManager) {
        this.orgManager = orgManager;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    @Override
    public String toString() {
        return "PmsOrganization{" +
                "orgUid='" + orgUid + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgParUid='" + orgParUid + '\'' +
                '}';
    }
}
