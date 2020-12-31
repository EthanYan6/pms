package com.buaa.pms.model;

import java.util.List;

public class OrgTableItem {
    private String orgUid;
    private String orgId;
    private String orgName;
    private String orgParUid;
    private String orgParName;
    private String orgManager;
    private String orgPhone;
    private String orgEmail;
    private String orgAddress;
    private String orgDescription;

    private String name;    // echarts图用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<OrgTableItem> children;

    public OrgTableItem() {}

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

    public String getOrgParName() {
        return orgParName;
    }

    public void setOrgParName(String orgParName) {
        this.orgParName = orgParName;
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

    public List<OrgTableItem> getChildren() {
        return children;
    }

    public void setChildren(List<OrgTableItem> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PmsOrgTableItem{" +
                "orgUid='" + orgUid + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgParUid='" + orgParUid + '\'' +
                ", orgParName='" + orgParName + '\'' +
                ", orgManager='" + orgManager + '\'' +
                ", orgPhone='" + orgPhone + '\'' +
                ", orgEmail='" + orgEmail + '\'' +
                ", orgAddress='" + orgAddress + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                ", children=" + children +
                '}';
    }
}
