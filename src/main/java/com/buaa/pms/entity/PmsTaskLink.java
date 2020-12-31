package com.buaa.pms.entity;

public class PmsTaskLink {

    private String taskLinkUid;
    private String taskLinkPreTaskUid;
    private String taskLinkSucTaskUid;
    private Integer taskLinkType;
    private String taskLinkProjUid;
    private String taskLinkProcUid;

    public String getTaskLinkUid() {
        return taskLinkUid;
    }

    public void setTaskLinkUid(String taskLinkUid) {
        this.taskLinkUid = taskLinkUid;
    }

    public String getTaskLinkPreTaskUid() {
        return taskLinkPreTaskUid;
    }

    public void setTaskLinkPreTaskUid(String taskLinkPreTaskUid) {
        this.taskLinkPreTaskUid = taskLinkPreTaskUid;
    }

    public String getTaskLinkSucTaskUid() {
        return taskLinkSucTaskUid;
    }

    public void setTaskLinkSucTaskUid(String taskLinkSucTaskUid) {
        this.taskLinkSucTaskUid = taskLinkSucTaskUid;
    }

    public Integer getTaskLinkType() {
        return taskLinkType;
    }

    public void setTaskLinkType(Integer taskLinkType) {
        this.taskLinkType = taskLinkType;
    }

    public String getTaskLinkProjUid() {
        return taskLinkProjUid;
    }

    public void setTaskLinkProjUid(String taskLinkProjUid) {
        this.taskLinkProjUid = taskLinkProjUid;
    }

    public String getTaskLinkProcUid() {
        return taskLinkProcUid;
    }

    public void setTaskLinkProcUid(String taskLinkProcUid) {
        this.taskLinkProcUid = taskLinkProcUid;
    }

    @Override
    public String toString() {
        return "PmsTaskLink{" +
                "taskLinkUid='" + taskLinkUid + '\'' +
                ", taskLinkPreTaskUid='" + taskLinkPreTaskUid + '\'' +
                ", taskLinkSucTaskUid='" + taskLinkSucTaskUid + '\'' +
                ", taskLinkType=" + taskLinkType +
                ", taskLinkProjUid='" + taskLinkProjUid + '\'' +
                ", taskLinkProcUid='" + taskLinkProcUid + '\'' +
                '}';
    }
}
