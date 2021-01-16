package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsTaskResPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsTaskResPlanMapper {

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
}
