package com.buaa.pms.service;

import com.buaa.pms.entity.PmsTaskResReq;
import com.buaa.pms.model.TaskResReq;

import java.util.List;

public interface PmsTaskResReqService {

    public List<PmsTaskResReq> selectAll();

    public List<PmsTaskResReq> selectByProjUid(String resReqProjUid);

    public List<PmsTaskResReq> selectByProcUidList(List<String> resReqProcUidList);

    public List<PmsTaskResReq> selectByProcUid(String resReqProcUid);

    public List<PmsTaskResReq> selectByTaskUid(String resReqTaskUid);

    public List<TaskResReq> getResReqListByTaskUid(String resReqTaskUid);

    public List<PmsTaskResReq> selectByResReqResPlanUid(String resReqResPlanUid);

    public List<TaskResReq> getResReqListByTaskResPlanUid(String resReqResPlanUid);

    public PmsTaskResReq selectByUid(String resReqUid);

    public void save(PmsTaskResReq pmsTaskResReq);

    public void deleteByUid(String resReqUid);

    public void deleteByResReqResPlanUid(String resReqResPlanUid);

    public void deleteByTaskUid(String resReqTaskUid);

    public void deleteByProjUid(String resReqProjUid);

    public void deleteByProcUid(String resReqProcUid);

    public void update(PmsTaskResReq pmsTaskResReq);

    public void updatePmsTaskResReqs(List<PmsTaskResReq> pmsTaskResReqs);

    public void saveOrUpdate(PmsTaskResReq pmsTaskResReq);

}
