package com.buaa.pms.service;

import com.buaa.pms.entity.PmsAllocateResource;
import com.buaa.pms.model.AllocateResource;
import com.buaa.pms.model.ChartAllocateResource;

import java.util.List;

public interface PmsAllocateResourceService {

    public List<PmsAllocateResource> selectAll();

    public List<PmsAllocateResource> selectByProjUid(String arProjUid);

    public List<PmsAllocateResource> selectByProcUid(String arProcUid);

    public List<PmsAllocateResource> selectByTaskUid(String arTaskUid);

    public List<PmsAllocateResource> selectByResUid(String arResUid);

    public List<PmsAllocateResource> selectByResUidList(List arResUidList);

    public List<AllocateResource> getAllocateResourceListByResUid(String arResUid);

    public PmsAllocateResource selectByUid(String arUid);

    public void save(PmsAllocateResource pmsAllocateResource);

    public void deleteByUid(String arUid);

    public void deleteByTaskUid(String arTaskUid);

    public void deleteByProjUid(String arProjUid);

    public void deleteByProcUid(String arProcUid);

    public void update(PmsAllocateResource pmsAllocateResource);

    public void saveOrUpdate(PmsAllocateResource pmsAllocateResource);

    public List<AllocateResource> getAllocateResourceFromPmsAllocateResource(List<PmsAllocateResource> pmsAllocateResources);

    public List<ChartAllocateResource> getChartAllocateResourceListByResUids(List<String> resUids, int resType);

}
