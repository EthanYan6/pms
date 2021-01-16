package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsTaskResReq;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsTaskResReqMapper {

    public List<PmsTaskResReq> selectAll();

    public List<PmsTaskResReq> selectByProjUid(String resReqProjUid);

    public List<PmsTaskResReq> selectByProcUidList(List<String> resReqProcUidList);

    public List<PmsTaskResReq> selectByProcUid(String resReqProcUid);

    public List<PmsTaskResReq> selectByTaskUid(String resReqTaskUid);

    public List<PmsTaskResReq> selectByResReqResPlanUid(String resReqResPlanUid);

    public PmsTaskResReq selectByUid(String resReqUid);

    public void save(PmsTaskResReq pmsTaskResReq);

    public void deleteByUid(String resReqUid);

    public void deleteByResReqResPlanUid(String resReqResPlanUid);

    public void deleteByTaskUid(String resReqTaskUid);

    public void deleteByProjUid(String resReqProjUid);

    public void deleteByProcUid(String resReqProcUid);

    public void update(PmsTaskResReq pmsTaskResReq);

    public void updatePmsTaskResReqs(List<PmsTaskResReq> pmsTaskResReqs);
}
