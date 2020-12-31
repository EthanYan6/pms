package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.*;
import com.buaa.pms.mapper.PmsAllocateResourceMapper;
import com.buaa.pms.model.AllocateResource;
import com.buaa.pms.model.ChartAllocateResource;
import com.buaa.pms.model.ChartAllocateResourceDeal;
import com.buaa.pms.service.*;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class PmsAllocateResourceServiceImp implements PmsAllocateResourceService {

    @Resource
    PmsAllocateResourceMapper pmsAllocateResourceMapper;

    @Resource
    PmsProjectService pmsProjectService;

    @Resource
    PmsTaskService pmsTaskService;

    @Resource
    PmsHumanService pmsHumanService;

    @Resource
    PmsEquipmentService pmsEquipmentService;

    @Override
    public List<PmsAllocateResource> selectAll() {
        return pmsAllocateResourceMapper.selectAll();
    }

    @Override
    public List<PmsAllocateResource> selectByProjUid(String arProjUid) {
        return pmsAllocateResourceMapper.selectByProjUid(arProjUid);
    }

    @Override
    public List<PmsAllocateResource> selectByProcUid(String arProcUid) {
        return pmsAllocateResourceMapper.selectByProcUid(arProcUid);
    }

    @Override
    public List<PmsAllocateResource> selectByTaskUid(String arTaskUid) {
        return pmsAllocateResourceMapper.selectByTaskUid(arTaskUid);
    }

    @Override
    public List<PmsAllocateResource> selectByResUid(String arResUid) {
        return pmsAllocateResourceMapper.selectByResUid(arResUid);
    }

    @Override
    public List<PmsAllocateResource> selectByResUidList(List arResUidList) {
        return pmsAllocateResourceMapper.selectByResUidList(arResUidList);
    }

    @Override
    public List<AllocateResource> getAllocateResourceListByResUid(String arResUid) {
        return this.getAllocateResourceFromPmsAllocateResource(this.selectByResUid(arResUid));
    }

    @Override
    public PmsAllocateResource selectByUid(String arUid) {
        return pmsAllocateResourceMapper.selectByUid(arUid);
    }

    @Override
    public void save(PmsAllocateResource pmsAllocateResource) {
        // 分配UUID
        pmsAllocateResource.setArUid(new MyUUID().getUUID());
        pmsAllocateResourceMapper.save(pmsAllocateResource);
    }

    @Override
    public void deleteByUid(String arUid) {
        pmsAllocateResourceMapper.deleteByUid(arUid);
    }

    @Override
    public void deleteByTaskUid(String arTaskUid) {
        pmsAllocateResourceMapper.deleteByTaskUid(arTaskUid);
    }

    @Override
    public void deleteByProcUid(String arProcUid) {
        pmsAllocateResourceMapper.deleteByProcUid(arProcUid);
    }

    @Override
    public void deleteByProjUid(String arProjUid) {
        pmsAllocateResourceMapper.deleteByProjUid(arProjUid);
    }

    @Override
    public void update(PmsAllocateResource pmsAllocateResource) {
        pmsAllocateResourceMapper.update(pmsAllocateResource);
    }

    @Override
    public void saveOrUpdate(PmsAllocateResource pmsAllocateResource) {
        if (pmsAllocateResource.getArUid() == null || pmsAllocateResource.getArUid().equals("")) {
            this.save(pmsAllocateResource);
        } else {
            this.update(pmsAllocateResource);
        }
    }

    @Override
    public List<AllocateResource> getAllocateResourceFromPmsAllocateResource(List<PmsAllocateResource> pmsAllocateResources) {
        List<AllocateResource> allocateResourceList = new LinkedList<>();
        for (PmsAllocateResource pmsAllocateResource : pmsAllocateResources) {
            AllocateResource ar = new AllocateResource(pmsAllocateResource);
            PmsTask pmsTask = pmsTaskService.selectByUid(pmsAllocateResource.getArTaskUid());
            PmsProject pmsProject = pmsProjectService.selectByUid(pmsAllocateResource.getArProjUid());
            ar.setArProjName(pmsProject.getProjName());
            ar.setArTaskName(pmsTask.getTaskName());
            allocateResourceList.add(ar);
        }
        return allocateResourceList;
    }

    @Override
    public List<ChartAllocateResource> getChartAllocateResourceListByResUids(List<String> resUids, int resType) {
        List<ChartAllocateResource> chartArList = new LinkedList<>();
        if (resType == 0) {
            for (String resUid : resUids) {
                List<PmsAllocateResource> pmsAllocateResources = this.selectByResUid(resUid);
                if (pmsAllocateResources.isEmpty()) {
                    continue;
                }
                ChartAllocateResource chartAr = new ChartAllocateResource();
                PmsHuman pmsHuman = pmsHumanService.selectByUid(resUid);
                chartAr.setResName(pmsHuman.getHumName());
                List<ChartAllocateResourceDeal> chartArDeals = new LinkedList<>();
                for (PmsAllocateResource pmsAllocateResource : pmsAllocateResources) {
                    ChartAllocateResourceDeal chartArDeal = new ChartAllocateResourceDeal();
                    chartArDeal.setProjName(pmsProjectService.selectByUid(pmsAllocateResource.getArProjUid()).getProjName());
                    chartArDeal.setTaskName(pmsTaskService.selectByUid(pmsAllocateResource.getArTaskUid()).getTaskName());
                    chartArDeal.setStartDateTime(pmsAllocateResource.getArResStartDateTime());
                    chartArDeal.setFinishDateTime(pmsAllocateResource.getArResFinishDateTime());
                    chartArDeals.add(chartArDeal);
                }
                chartAr.setDeals(chartArDeals);
                chartArList.add(chartAr);
            }
        }
        if (resType == 1) {
            for (String resUid : resUids) {
                List<PmsAllocateResource> pmsAllocateResources = this.selectByResUid(resUid);
                if (pmsAllocateResources.isEmpty()) {
                    continue;
                }
                ChartAllocateResource chartAr = new ChartAllocateResource();
                PmsEquipment pmsEquipment = pmsEquipmentService.selectByUid(resUid);
                chartAr.setResName(pmsEquipment.getEquipName());
                List<ChartAllocateResourceDeal> chartArDeals = new LinkedList<>();
                for (PmsAllocateResource pmsAllocateResource : pmsAllocateResources) {
                    ChartAllocateResourceDeal chartArDeal = new ChartAllocateResourceDeal();
                    chartArDeal.setProjName(pmsProjectService.selectByUid(pmsAllocateResource.getArProjUid()).getProjName());
                    chartArDeal.setTaskName(pmsTaskService.selectByUid(pmsAllocateResource.getArTaskUid()).getTaskName());
                    chartArDeal.setStartDateTime(pmsAllocateResource.getArResStartDateTime());
                    chartArDeal.setFinishDateTime(pmsAllocateResource.getArResFinishDateTime());
                    chartArDeals.add(chartArDeal);
                }
                chartAr.setDeals(chartArDeals);
                chartArList.add(chartAr);
            }
        }
        return chartArList;
    }
}
