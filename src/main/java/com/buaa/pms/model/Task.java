package com.buaa.pms.model;

import com.buaa.pms.entity.PmsGroup;
import com.buaa.pms.entity.PmsTask;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    private PmsTask pmsTask;
    // 紧前任务集合
    private List<PmsTask> taskNormalPreTasks;
    private List<PmsTask> taskRealPreTasks;

    // 紧后任务集合
    private List<PmsTask> taskNormalSucTasks;
    private List<PmsTask> taskRealSucTasks;

    // 所属任务组
    private PmsGroup pmsGroup;

    public Task() {}

    public Task(PmsTask pmsTask) {
        this.pmsTask = pmsTask;
    }

//    public Task(PmsTask pmsTask, List<PmsTask> taskNormalPreTasks, List<PmsTask> taskRealPreTasks) {
//        this.pmsTask = pmsTask;
//        this.taskNormalPreTasks = taskNormalPreTasks;
//        this.taskRealPreTasks = taskRealPreTasks;
//    }

    public Task(PmsTask pmsTask, List<PmsTask> taskNormalPreTasks, PmsGroup pmsGroup) {
        this.pmsTask = pmsTask;
        this.taskNormalPreTasks = taskNormalPreTasks;
        this.pmsGroup = pmsGroup;
    }

    public Task(PmsTask pmsTask, List<PmsTask> taskNormalPreTasks, List<PmsTask> taskNormalSucTasks, PmsGroup pmsGroup) {
        this.pmsTask = pmsTask;
        this.taskNormalPreTasks = taskNormalPreTasks;
        this.taskNormalSucTasks = taskNormalSucTasks;
        this.pmsGroup = pmsGroup;
    }

    public Task(PmsTask pmsTask, List<PmsTask> taskNormalPreTasks) {
        this.pmsTask = pmsTask;
        this.taskNormalPreTasks = taskNormalPreTasks;
    }

    public PmsTask getPmsTask() {
        return pmsTask;
    }

    public void setPmsTask(PmsTask pmsTask) {
        this.pmsTask = pmsTask;
    }

    public List<PmsTask> getTaskNormalPreTasks() {
        return taskNormalPreTasks;
    }

    public void setTaskNormalPreTasks(List<PmsTask> taskNormalPreTasks) {
        this.taskNormalPreTasks = taskNormalPreTasks;
    }

    public List<PmsTask> getTaskRealPreTasks() {
        return taskRealPreTasks;
    }

    public void setTaskRealPreTasks(List<PmsTask> taskRealPreTasks) {
        this.taskRealPreTasks = taskRealPreTasks;
    }

    public List<PmsTask> getTaskNormalSucTasks() {
        return taskNormalSucTasks;
    }

    public void setTaskNormalSucTasks(List<PmsTask> taskNormalSucTasks) {
        this.taskNormalSucTasks = taskNormalSucTasks;
    }

    public List<PmsTask> getTaskRealSucTasks() {
        return taskRealSucTasks;
    }

    public void setTaskRealSucTasks(List<PmsTask> taskRealSucTasks) {
        this.taskRealSucTasks = taskRealSucTasks;
    }

    public PmsGroup getPmsGroup() {
        return pmsGroup;
    }

    public void setPmsGroup(PmsGroup pmsGroup) {
        this.pmsGroup = pmsGroup;
    }
}
