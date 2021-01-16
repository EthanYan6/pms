package com.buaa.pms.model;

import java.util.List;

public class ResUidsResType {
    private int resType;
    private List<String> resUids;

    public int getResType() {
        return resType;
    }

    public void setResType(int resType) {
        this.resType = resType;
    }

    public List<String> getResUids() {
        return resUids;
    }

    public void setResUids(List<String> resUids) {
        this.resUids = resUids;
    }
}
