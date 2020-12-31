package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsTask {

    private String taskUid;
    private Integer taskId;
    private String taskName;
    private Integer taskType;
    private Integer taskInType;
    private Integer taskOutType;
    private String taskProjUid;
    private String taskProcUid;
    private String taskParUid;
    private String taskManager;
    private String taskDescription;
    private Integer taskPriority;
    private Timestamp taskPlanStartDateTime;
    private Timestamp taskPlanFinishDateTime;
    private Float taskPlanDur;
    private Timestamp taskEarlyStartDateTime;
    private Timestamp taskLateFinishDateTime;
    private Timestamp taskActStartDateTime;
    private Timestamp taskActFinishDateTime;
    private Integer taskActDur;
    private Integer taskWork;
    private Integer taskCompWork;
    private Integer taskRemWork;
    private Float taskPctWork;
    private Integer taskWorkModel;
    private Integer taskState;

    public String getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(String taskUid) {
        this.taskUid = taskUid;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskInType() {
        return taskInType;
    }

    public void setTaskInType(Integer taskInType) {
        this.taskInType = taskInType;
    }

    public Integer getTaskOutType() {
        return taskOutType;
    }

    public void setTaskOutType(Integer taskOutType) {
        this.taskOutType = taskOutType;
    }

    public String getTaskProjUid() {
        return taskProjUid;
    }

    public void setTaskProjUid(String taskProjUid) {
        this.taskProjUid = taskProjUid;
    }

    public String getTaskProcUid() {
        return taskProcUid;
    }

    public void setTaskProcUid(String taskProcUid) {
        this.taskProcUid = taskProcUid;
    }

    public String getTaskParUid() {
        return taskParUid;
    }

    public void setTaskParUid(String taskParUid) {
        this.taskParUid = taskParUid;
    }

    public String getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(String taskManager) {
        this.taskManager = taskManager;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Timestamp getTaskPlanStartDateTime() {
        return taskPlanStartDateTime;
    }

    public void setTaskPlanStartDateTime(Timestamp taskPlanStartDateTime) {
        this.taskPlanStartDateTime = taskPlanStartDateTime;
    }

    public Timestamp getTaskPlanFinishDateTime() {
        return taskPlanFinishDateTime;
    }

    public void setTaskPlanFinishDateTime(Timestamp taskPlanFinishDateTime) {
        this.taskPlanFinishDateTime = taskPlanFinishDateTime;
    }

    public Float getTaskPlanDur() {
        return taskPlanDur;
    }

    public void setTaskPlanDur(Float taskPlanDur) {
        this.taskPlanDur = taskPlanDur;
    }

    public Timestamp getTaskEarlyStartDateTime() {
        return taskEarlyStartDateTime;
    }

    public void setTaskEarlyStartDateTime(Timestamp taskEarlyStartDateTime) {
        this.taskEarlyStartDateTime = taskEarlyStartDateTime;
    }

    public Timestamp getTaskLateFinishDateTime() {
        return taskLateFinishDateTime;
    }

    public void setTaskLateFinishDateTime(Timestamp taskLateFinishDateTime) {
        this.taskLateFinishDateTime = taskLateFinishDateTime;
    }

    public Timestamp getTaskActStartDateTime() {
        return taskActStartDateTime;
    }

    public void setTaskActStartDateTime(Timestamp taskActStartDateTime) {
        this.taskActStartDateTime = taskActStartDateTime;
    }

    public Timestamp getTaskActFinishDateTime() {
        return taskActFinishDateTime;
    }

    public void setTaskActFinishDateTime(Timestamp taskActFinishDateTime) {
        this.taskActFinishDateTime = taskActFinishDateTime;
    }

    public Integer getTaskActDur() {
        return taskActDur;
    }

    public void setTaskActDur(Integer taskActDur) {
        this.taskActDur = taskActDur;
    }

    public Integer getTaskWork() {
        return taskWork;
    }

    public void setTaskWork(Integer taskWork) {
        this.taskWork = taskWork;
    }

    public Integer getTaskCompWork() {
        return taskCompWork;
    }

    public void setTaskCompWork(Integer taskCompWork) {
        this.taskCompWork = taskCompWork;
    }

    public Integer getTaskRemWork() {
        return taskRemWork;
    }

    public void setTaskRemWork(Integer taskRemWork) {
        this.taskRemWork = taskRemWork;
    }

    public Float getTaskPctWork() {
        return taskPctWork;
    }

    public void setTaskPctWork(Float taskPctWork) {
        this.taskPctWork = taskPctWork;
    }

    public Integer getTaskWorkModel() {
        return taskWorkModel;
    }

    public void setTaskWorkModel(Integer taskWorkModel) {
        this.taskWorkModel = taskWorkModel;
    }

    public Integer getTaskState() {
        return taskState;
    }

    public void setTaskState(Integer taskState) {
        this.taskState = taskState;
    }
}
