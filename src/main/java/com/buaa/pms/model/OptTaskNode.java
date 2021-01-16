package com.buaa.pms.model;

import com.buaa.pms.entity.PmsTask;
import com.buaa.pms.entity.PmsTaskResReq;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class OptTaskNode {
    private PmsTask pmsTask;

    private int taskNo;

    // 实际紧前任务，有真紧前任务时等于真紧前任务集合，否则等于普通紧前任务集合
    private List<OptTaskNode> preTasks;
    // 实际紧后任务
    private List<OptTaskNode> sucTasks;
    // 普通紧前任务
    private List<OptTaskNode> normalPreTasks;
    // 普通紧后任务
    private List<OptTaskNode> normalSucTasks;
    // 真紧前任务
    private List<OptTaskNode> realPreTasks;
    // 真紧后任务
    private List<OptTaskNode> realSucTasks;
    // 所需资源集合，List的下标对应编码后半段中的数字，即第几个资源方案；Pair的key为资源方案UID，value为该方案包含的资源需求
    private List<Pair<String, List<PmsTaskResReq>>> resPlanReqPairList;
    // 资源方案的个数
    private int resPlanCount;
    // 任务所选资源方案在编码(数组)中对应位置的下标，即基因位坐标(表示编码的数组的下标)，以便解码时快速定位
    private int resPlanGenIndex;

    // 互斥任务集合（不能与该任务同时执行的任务）
    List<OptTaskNode> mutexTasks;
    // 紧前任务数量
    private int preTaskCount;
    // 当前紧前任务数量
    private int curPreTaskCount;
    // 紧后任务数量
    private int sucTaskCount;
    // 当前紧后任务数量
    private int curSucTaskCount;

    // 项目包含最多任务数量的路径中的任务数量
    private double maxProjPathTaskCount;

    // 后续任务数量（包括自身）最多的路径上的任务个数
    private double sucTaskCountSum;
    // 后续任务工期和（包括自身）最大的路径上的任务工期和
    private double sucTaskDurSum;
    // 任务最晚完成时间与优化起点日期相差的天数
    private double lateFinish;
    // 重要性
    private double importance;

    // 后续任务数量规则项的值
    private double sucTaskCountSumValue;
    // 后续任务工期和规则项的值
    private double sucTaskDurSumValue;
    // 任务最晚完成时间规则项的值
    private double lateFinishValue;
    // 任务重要性规则项的值
    private double importanceValue;

    // 任务优先级综合评价值
    private double priorityValue;

    public OptTaskNode() {
        preTasks = new ArrayList<>();
        sucTasks = new ArrayList<>();
        normalPreTasks = new ArrayList<>();
        normalSucTasks = new ArrayList<>();
        realPreTasks = new ArrayList<>();
        realSucTasks = new ArrayList<>();
        mutexTasks = new ArrayList<>();
        resPlanReqPairList = new ArrayList<>();
    }

    public OptTaskNode(PmsTask pmsTask) {
        preTasks = new ArrayList<>();
        sucTasks = new ArrayList<>();
        normalPreTasks = new ArrayList<>();
        normalSucTasks = new ArrayList<>();
        realPreTasks = new ArrayList<>();
        realSucTasks = new ArrayList<>();
        mutexTasks = new ArrayList<>();
        resPlanReqPairList = new ArrayList<>();

        pmsTask.setTaskPlanStartDateTime(null);
        pmsTask.setTaskPlanFinishDateTime(null);
        this.pmsTask = pmsTask;
    }

    public PmsTask getPmsTask() {
        return pmsTask;
    }

    public void setPmsTask(PmsTask pmsTask) {
        this.pmsTask = pmsTask;
    }

    public int getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(int taskNo) {
        this.taskNo = taskNo;
    }

    public List<OptTaskNode> getPreTasks() {
        return preTasks;
    }

    public void setPreTasks(List<OptTaskNode> preTasks) {
        this.preTasks = preTasks;
    }

    public List<OptTaskNode> getSucTasks() {
        return sucTasks;
    }

    public void setSucTasks(List<OptTaskNode> sucTasks) {
        this.sucTasks = sucTasks;
    }

    public List<OptTaskNode> getNormalPreTasks() {
        return normalPreTasks;
    }

    public void setNormalPreTasks(List<OptTaskNode> normalPreTasks) {
        this.normalPreTasks = normalPreTasks;
    }

    public List<OptTaskNode> getNormalSucTasks() {
        return normalSucTasks;
    }

    public void setNormalSucTasks(List<OptTaskNode> normalSucTasks) {
        this.normalSucTasks = normalSucTasks;
    }

    public List<OptTaskNode> getRealPreTasks() {
        return realPreTasks;
    }

    public void setRealPreTasks(List<OptTaskNode> realPreTasks) {
        this.realPreTasks = realPreTasks;
    }

    public List<OptTaskNode> getRealSucTasks() {
        return realSucTasks;
    }

    public void setRealSucTasks(List<OptTaskNode> realSucTasks) {
        this.realSucTasks = realSucTasks;
    }

    public List<Pair<String, List<PmsTaskResReq>>> getResPlanReqPairList() {
        return resPlanReqPairList;
    }

    public void setResPlanReqPairList(List<Pair<String, List<PmsTaskResReq>>> resPlanReqPairList) {
        this.resPlanReqPairList = resPlanReqPairList;
    }

    public int getResPlanCount() {
        return resPlanCount;
    }

    public void setResPlanCount(int resPlanCount) {
        this.resPlanCount = resPlanCount;
    }

    public int getResPlanGenIndex() {
        return resPlanGenIndex;
    }

    public void setResPlanGenIndex(int resPlanGenIndex) {
        this.resPlanGenIndex = resPlanGenIndex;
    }

    public List<OptTaskNode> getMutexTasks() {
        return mutexTasks;
    }

    public void setMutexTasks(List<OptTaskNode> mutexTasks) {
        this.mutexTasks = mutexTasks;
    }

    public int getPreTaskCount() {
        return preTaskCount;
    }

    public void setPreTaskCount(int preTaskCount) {
        this.preTaskCount = preTaskCount;
    }

    public int getCurPreTaskCount() {
        return curPreTaskCount;
    }

    public void setCurPreTaskCount(int curPreTaskCount) {
        this.curPreTaskCount = curPreTaskCount;
    }

    public int getSucTaskCount() {
        return sucTaskCount;
    }

    public void setSucTaskCount(int sucTaskCount) {
        this.sucTaskCount = sucTaskCount;
    }

    public int getCurSucTaskCount() {
        return curSucTaskCount;
    }

    public void setCurSucTaskCount(int curSucTaskCount) {
        this.curSucTaskCount = curSucTaskCount;
    }

    public double getMaxProjPathTaskCount() {
        return maxProjPathTaskCount;
    }

    public void setMaxProjPathTaskCount(double maxProjPathTaskCount) {
        this.maxProjPathTaskCount = maxProjPathTaskCount;
    }

    public double getSucTaskCountSum() {
        return sucTaskCountSum;
    }

    public void setSucTaskCountSum(double sucTaskCountSum) {
        this.sucTaskCountSum = sucTaskCountSum;
    }

    public double getSucTaskDurSum() {
        return sucTaskDurSum;
    }

    public void setSucTaskDurSum(double sucTaskDurSum) {
        this.sucTaskDurSum = sucTaskDurSum;
    }

    public double getLateFinish() {
        return lateFinish;
    }

    public void setLateFinish(double lateFinish) {
        this.lateFinish = lateFinish;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    public double getSucTaskCountSumValue() {
        return sucTaskCountSumValue;
    }

    public void setSucTaskCountSumValue(double sucTaskCountSumValue) {
        this.sucTaskCountSumValue = sucTaskCountSumValue;
    }

    public double getSucTaskDurSumValue() {
        return sucTaskDurSumValue;
    }

    public void setSucTaskDurSumValue(double sucTaskDurSumValue) {
        this.sucTaskDurSumValue = sucTaskDurSumValue;
    }

    public double getLateFinishValue() {
        return lateFinishValue;
    }

    public void setLateFinishValue(double lateFinishValue) {
        this.lateFinishValue = lateFinishValue;
    }

    public double getImportanceValue() {
        return importanceValue;
    }

    public void setImportanceValue(double importanceValue) {
        this.importanceValue = importanceValue;
    }

    public double getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(double priorityValue) {
        this.priorityValue = priorityValue;
    }
}
