package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.*;
import com.buaa.pms.mapper.PmsTaskResReqMapper;
import com.buaa.pms.model.TaskResReq;
import com.buaa.pms.service.*;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsTaskResReqServiceImp implements PmsTaskResReqService {

    @Resource
    PmsTaskResReqMapper pmsTaskResReqMapper;

    @Resource
    PmsHumanService pmsHumanService;

    @Resource
    PmsEquipmentService pmsEquipmentService;

    @Resource
    PmsPlaceService pmsPlaceService;

    @Resource
    PmsKnowledgeService pmsKnowledgeService;

    @Override
    public List<PmsTaskResReq> selectAll() {
        return pmsTaskResReqMapper.selectAll();
    }

    @Override
    public List<PmsTaskResReq> selectByProjUid(String resReqProjUid) {
        return pmsTaskResReqMapper.selectByProjUid(resReqProjUid);
    }

    @Override
    public List<PmsTaskResReq> selectByProcUidList(List<String> resReqProcUidList) {
        if (resReqProcUidList != null && !resReqProcUidList.isEmpty())
            return pmsTaskResReqMapper.selectByProcUidList(resReqProcUidList);
        return null;
    }

    @Override
    public List<PmsTaskResReq> selectByProcUid(String resReqProcUid) {
        return pmsTaskResReqMapper.selectByProcUid(resReqProcUid);
    }

    @Override
    public List<PmsTaskResReq> selectByTaskUid(String resReqTaskUid) {
        return pmsTaskResReqMapper.selectByTaskUid(resReqTaskUid);
    }

    @Override
    public List<TaskResReq> getResReqListByTaskUid(String resReqTaskUid) {
        List<TaskResReq> taskResReqList = new ArrayList<>();
        List<PmsTaskResReq> pmsTaskResReqs = this.selectByTaskUid(resReqTaskUid);
        for (PmsTaskResReq pmsTaskResReq : pmsTaskResReqs) {
            taskResReqList.add(this.getTaskResReqFromPmsTaskResReq(pmsTaskResReq));
        }
        return taskResReqList;
    }

    @Override
    public List<PmsTaskResReq> selectByResReqResPlanUid(String resReqResPlanUid) {
        return pmsTaskResReqMapper.selectByResReqResPlanUid(resReqResPlanUid);
    }

    @Override
    public List<TaskResReq> getResReqListByTaskResPlanUid(String resReqResPlanUid) {
        List<TaskResReq> taskResReqList = new ArrayList<>();
        List<PmsTaskResReq> pmsTaskResReqs = this.selectByResReqResPlanUid(resReqResPlanUid);
        for (PmsTaskResReq pmsTaskResReq : pmsTaskResReqs) {
            taskResReqList.add(this.getTaskResReqFromPmsTaskResReq(pmsTaskResReq));
        }
        return taskResReqList;
    }

    @Override
    public PmsTaskResReq selectByUid(String resReqUid) {
        return pmsTaskResReqMapper.selectByUid(resReqUid);
    }

    @Override
    public void save(PmsTaskResReq pmsTaskResReq) {
        // 分配UUID
        pmsTaskResReq.setResReqUid(new MyUUID().getUUID());
        pmsTaskResReqMapper.save(pmsTaskResReq);
    }

    @Override
    public void deleteByUid(String resReqUid) {
        pmsTaskResReqMapper.deleteByUid(resReqUid);
    }

    @Override
    public void deleteByResReqResPlanUid(String resReqResPlanUid) {
        pmsTaskResReqMapper.deleteByResReqResPlanUid(resReqResPlanUid);
    }

    @Override
    public void deleteByTaskUid(String resReqTaskUid) {
        pmsTaskResReqMapper.deleteByTaskUid(resReqTaskUid);
    }

    @Override
    public void deleteByProcUid(String resReqProcUid) {
        pmsTaskResReqMapper.deleteByProcUid(resReqProcUid);
    }

    @Override
    public void deleteByProjUid(String resReqProjUid) {
        pmsTaskResReqMapper.deleteByProjUid(resReqProjUid);
    }

    @Override
    public void update(PmsTaskResReq pmsTaskResReq) {
        pmsTaskResReqMapper.update(pmsTaskResReq);
    }

    @Override
    public void updatePmsTaskResReqs(List<PmsTaskResReq> pmsTaskResReqs) {
        if (pmsTaskResReqs != null && !pmsTaskResReqs.isEmpty())
            pmsTaskResReqMapper.updatePmsTaskResReqs(pmsTaskResReqs);
    }

    @Override
    public void saveOrUpdate(PmsTaskResReq pmsTaskResReq) {
        if (pmsTaskResReq.getResReqUid() == null || pmsTaskResReq.getResReqUid().equals("")) {
            this.save(pmsTaskResReq);
        } else {
            this.update(pmsTaskResReq);
        }
    }

    private TaskResReq getTaskResReqFromPmsTaskResReq(PmsTaskResReq pmsTaskResReq) {
        TaskResReq resReq = new TaskResReq();
        resReq.setResReqUid(pmsTaskResReq.getResReqUid());
        resReq.setResReqId(pmsTaskResReq.getResReqId());
        resReq.setResReqResPlanUid(pmsTaskResReq.getResReqResPlanUid());
        resReq.setResReqProjUid(pmsTaskResReq.getResReqProjUid());
        resReq.setResReqProcUid(pmsTaskResReq.getResReqProcUid());
        resReq.setResReqTaskUid(pmsTaskResReq.getResReqTaskUid());
        resReq.setResReqResType(pmsTaskResReq.getResReqResType());
        resReq.setResReqResUid(pmsTaskResReq.getResReqResUid());
        resReq.setResReqResStartDateTime(pmsTaskResReq.getResReqResStartDateTime());
        resReq.setResReqResFinishDateTime(pmsTaskResReq.getResReqResFinishDateTime());
        resReq.setResReqResWork(pmsTaskResReq.getResReqResWork());
        resReq.setResReqResWorkModel(pmsTaskResReq.getResReqResWorkModel());
        resReq.setResReqResAmount(pmsTaskResReq.getResReqResAmount());

        switch (pmsTaskResReq.getResReqResType()) {
            case 0 :
                PmsHuman human = pmsHumanService.selectByUid(pmsTaskResReq.getResReqResUid());
                resReq.setResId(human.getHumId());
                resReq.setResName(human.getHumName());
                resReq.setResAbilityType(human.getHumAbilityType());
                break;
            case 1 :
                PmsEquipment equip = pmsEquipmentService.selectByUid(pmsTaskResReq.getResReqResUid());
                resReq.setResId(equip.getEquipId());
                resReq.setResName(equip.getEquipName());
                resReq.setResAbilityType(equip.getEquipCapType());
                break;
            case 2 :
                PmsPlace place = pmsPlaceService.selectByUid(pmsTaskResReq.getResReqResUid());
                resReq.setResId(place.getPlaceId());
                resReq.setResName(place.getPlaceName());
                resReq.setResAbilityType(place.getPlaceType().toString());
                break;
            case 3 :
                PmsKnowledge knowledge = pmsKnowledgeService.selectByUid(pmsTaskResReq.getResReqResUid());
                resReq.setResId(knowledge.getKnowlId());
                resReq.setResName(knowledge.getKnowlName());
                resReq.setResAbilityType(knowledge.getKnowlType());
                break;
            default: return resReq;
        }
        return resReq;
    }
}
