package com.buaa.pms.service;

import com.buaa.pms.entity.PmsTaskResPlan;

import java.util.List;

public interface PmsTaskResPlanService {

    public List<PmsTaskResPlan> selectAll();

    public List<PmsTaskResPlan> selectByProjUid(String resPlanProjUid);

    public List<PmsTaskResPlan> selectByProcUidList(List<String> resPlanProcUidList);

    public List<PmsTaskResPlan> selectByProcUid(String resPlanProcUid);

    public List<PmsTaskResPlan> selectByTaskUid(String resPlanTaskUid);

    public PmsTaskResPlan selectByUid(String resPlanUid);

    public void save(PmsTaskResPlan pmsTaskResPlan);

    public void deleteByUid(String resPlanUid);

    public void deleteByTaskUid(String resPlanTaskUid);

    public void deleteByProjUid(String resPlanProjUid);

    public void deleteByProcUid(String resPlanProcUid);

    public void update(PmsTaskResPlan pmsTaskResPlan);

    public void updatePmsTaskResPlans(List<PmsTaskResPlan> pmsTaskResPlans);

    public void saveOrUpdate(PmsTaskResPlan pmsTaskResPlan);

}
