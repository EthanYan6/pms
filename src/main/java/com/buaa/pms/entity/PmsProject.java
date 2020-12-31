package com.buaa.pms.entity;

import java.sql.Timestamp;

public class PmsProject {

    private String projUid;
    private String projId;
    private String projName;
    private String projTaskUid;
    private String projParUid;
    private String projOrgUid;
    private String projManager;
    private String projDescription;
    private Integer projPriority;
    private Timestamp projPlanStartDateTime;
    private Timestamp projPlanFinishDateTime;
    private Integer projPlanDur;
    private Timestamp projEarlyStartDateTime;
    private Timestamp projLateFinishDateTime;
    private Timestamp projActStartDateTime;
    private Timestamp projActFinishDateTime;
    private Integer projActDur;
    private Integer projWork;
    private Integer projCompWork;
    private Integer projRemWork;
    private Float projPctWork;
    private Integer projState;

    public String getProjUid() {
        return projUid;
    }

    public void setProjUid(String projUid) {
        this.projUid = projUid;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjTaskUid() {
        return projTaskUid;
    }

    public void setProjTaskUid(String projTaskUid) {
        this.projTaskUid = projTaskUid;
    }

    public String getProjParUid() {
        return projParUid;
    }

    public void setProjParUid(String projParUid) {
        this.projParUid = projParUid;
    }

    public String getProjOrgUid() {
        return projOrgUid;
    }

    public void setProjOrgUid(String projOrgUid) {
        this.projOrgUid = projOrgUid;
    }

    public String getProjManager() {
        return projManager;
    }

    public void setProjManager(String projManager) {
        this.projManager = projManager;
    }

    public String getProjDescription() {
        return projDescription;
    }

    public void setProjDescription(String projDescription) {
        this.projDescription = projDescription;
    }

    public Integer getProjPriority() {
        return projPriority;
    }

    public void setProjPriority(Integer projPriority) {
        this.projPriority = projPriority;
    }

    public Timestamp getProjPlanStartDateTime() {
        return projPlanStartDateTime;
    }

    public void setProjPlanStartDateTime(Timestamp projPlanStartDateTime) {
        this.projPlanStartDateTime = projPlanStartDateTime;
    }

    public Timestamp getProjPlanFinishDateTime() {
        return projPlanFinishDateTime;
    }

    public void setProjPlanFinishDateTime(Timestamp projPlanFinishDateTime) {
        this.projPlanFinishDateTime = projPlanFinishDateTime;
    }

    public Integer getProjPlanDur() {
        return projPlanDur;
    }

    public void setProjPlanDur(Integer projPlanDur) {
        this.projPlanDur = projPlanDur;
    }

    public Timestamp getProjEarlyStartDateTime() {
        return projEarlyStartDateTime;
    }

    public void setProjEarlyStartDateTime(Timestamp projEarlyStartDateTime) {
        this.projEarlyStartDateTime = projEarlyStartDateTime;
    }

    public Timestamp getProjLateFinishDateTime() {
        return projLateFinishDateTime;
    }

    public void setProjLateFinishDateTime(Timestamp projLateFinishDateTime) {
        this.projLateFinishDateTime = projLateFinishDateTime;
    }

    public Timestamp getProjActStartDateTime() {
        return projActStartDateTime;
    }

    public void setProjActStartDateTime(Timestamp projActStartDateTime) {
        this.projActStartDateTime = projActStartDateTime;
    }

    public Timestamp getProjActFinishDateTime() {
        return projActFinishDateTime;
    }

    public void setProjActFinishDateTime(Timestamp projActFinishDateTime) {
        this.projActFinishDateTime = projActFinishDateTime;
    }

    public Integer getProjActDur() {
        return projActDur;
    }

    public void setProjActDur(Integer projActDur) {
        this.projActDur = projActDur;
    }

    public Integer getProjWork() {
        return projWork;
    }

    public void setProjWork(Integer projWork) {
        this.projWork = projWork;
    }

    public Integer getProjCompWork() {
        return projCompWork;
    }

    public void setProjCompWork(Integer projCompWork) {
        this.projCompWork = projCompWork;
    }

    public Integer getProjRemWork() {
        return projRemWork;
    }

    public void setProjRemWork(Integer projRemWork) {
        this.projRemWork = projRemWork;
    }

    public Float getProjPctWork() {
        return projPctWork;
    }

    public void setProjPctWork(Float projPctWork) {
        this.projPctWork = projPctWork;
    }

    public Integer getProjState() {
        return projState;
    }

    public void setProjState(Integer projState) {
        this.projState = projState;
    }
}
