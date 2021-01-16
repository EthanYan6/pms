package com.buaa.pms.model;

import com.buaa.pms.entity.PmsAllocateResource;

public class AllocateResource {
    private PmsAllocateResource pmsAllocateResource;
    private String arResName;
    private String arResId;
    private String arProjName;
    private String arTaskName;

    public AllocateResource() {
    }

    public AllocateResource(PmsAllocateResource pmsAllocateResource) {
        this.pmsAllocateResource = pmsAllocateResource;
    }

    public PmsAllocateResource getPmsAllocateResource() {
        return pmsAllocateResource;
    }

    public void setPmsAllocateResource(PmsAllocateResource pmsAllocateResource) {
        this.pmsAllocateResource = pmsAllocateResource;
    }

    public String getArResName() {
        return arResName;
    }

    public void setArResName(String arResName) {
        this.arResName = arResName;
    }

    public String getArResId() {
        return arResId;
    }

    public void setArResId(String arResId) {
        this.arResId = arResId;
    }

    public String getArProjName() {
        return arProjName;
    }

    public void setArProjName(String arProjName) {
        this.arProjName = arProjName;
    }

    public String getArTaskName() {
        return arTaskName;
    }

    public void setArTaskName(String arTaskName) {
        this.arTaskName = arTaskName;
    }
}
