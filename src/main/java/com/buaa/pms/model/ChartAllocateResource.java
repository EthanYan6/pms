package com.buaa.pms.model;

import java.util.List;

public class ChartAllocateResource {
    String resName;
    List<ChartAllocateResourceDeal> deals;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public List<ChartAllocateResourceDeal> getDeals() {
        return deals;
    }

    public void setDeals(List<ChartAllocateResourceDeal> deals) {
        this.deals = deals;
    }
}
