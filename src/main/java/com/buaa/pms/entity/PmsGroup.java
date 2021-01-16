package com.buaa.pms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PmsGroup {

    private String groupUid;
    private String groupId;
    private String groupName;
    private String groupProcUid;
    private String groupProjUid;

    public String getGroupUid() {
        return groupUid;
    }

    public void setGroupUid(String groupUid) {
        this.groupUid = groupUid;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupProcUid() {
        return groupProcUid;
    }

    public void setGroupProcUid(String groupProcUid) {
        this.groupProcUid = groupProcUid;
    }

    public String getGroupProjUid() {
        return groupProjUid;
    }

    public void setGroupProjUid(String groupProjUid) {
        this.groupProjUid = groupProjUid;
    }
}
