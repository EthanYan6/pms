package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.entity.PmsKnowledge;
import com.buaa.pms.mapper.PmsKnowledgeMapper;
import com.buaa.pms.model.Knowledge;
import com.buaa.pms.service.PmsOrganizationService;
import com.buaa.pms.service.PmsKnowledgeService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsKnowledgeServiceImp implements PmsKnowledgeService {
    @Resource
    PmsKnowledgeMapper  pmsKnowledgeMapper;

    @Resource
    PmsOrganizationService pmsOrganizationService;

    @Override
    public List<PmsKnowledge> selectAll() {
        return pmsKnowledgeMapper.selectAll();
    }

    @Override
    public List<Knowledge> getKnowledgeList() {
        List<Knowledge> knowlList = new ArrayList<>();
        for (PmsKnowledge pmsKnowledge : this.selectAll()) {
            Knowledge knowl = this.getKnowledgeFromPmsKnowledge(pmsKnowledge);
            knowlList.add(knowl);
        }
        return knowlList;
    }

    @Override
    public List<PmsKnowledge> selectByOrgUid(String knowlOrgUid) {
        return pmsKnowledgeMapper.selectByOrgUid(knowlOrgUid);
    }

    @Override
    public List<Knowledge> getKnowledgeListByOrgUid(String knowlOrgUid) {
        List<Knowledge> knowlList = new ArrayList<>();
        for (PmsKnowledge pmsKnowledge : this.selectByOrgUid(knowlOrgUid)) {
            Knowledge knowl = this.getKnowledgeFromPmsKnowledge(pmsKnowledge);
            knowlList.add(knowl);
        }
        return knowlList;
    }

    @Override
    public PmsKnowledge selectByUid(String knowlUid) {
        return pmsKnowledgeMapper.selectByUid(knowlUid);
    }

    @Override
    public void save(PmsKnowledge pmsKnowledge) {
        pmsKnowledge.setKnowlUid(new MyUUID().getUUID());
        pmsKnowledgeMapper.save(pmsKnowledge);
    }

    @Override
    public void deleteByUid(String knowlUid) {
        pmsKnowledgeMapper.deleteByUid(knowlUid);
    }

    @Override
    public void update(PmsKnowledge pmsKnowledge) {
        pmsKnowledgeMapper.update(pmsKnowledge);
    }

    @Override
    public void saveOrUpdate(PmsKnowledge pmsKnowledge) {
        if (pmsKnowledge.getKnowlUid() == null || pmsKnowledge.getKnowlUid().equals("")) {
            this.save(pmsKnowledge);
        } else {
            this.update(pmsKnowledge);
        }
    }

    private Knowledge getKnowledgeFromPmsKnowledge(PmsKnowledge pmsKnowledge) {
        Knowledge knowl = new Knowledge();
        knowl.setKnowlUid(pmsKnowledge.getKnowlUid());
        knowl.setKnowlId(pmsKnowledge.getKnowlId());
        knowl.setKnowlName(pmsKnowledge.getKnowlName());
        knowl.setKnowlOrgUid(pmsKnowledge.getKnowlOrgUid());
        PmsOrganization knowlOrg = pmsOrganizationService.selectByUid(pmsKnowledge.getKnowlOrgUid());
        knowl.setKnowlOrgName(knowlOrg == null ? "" : knowlOrg.getOrgName());
        knowl.setKnowlType(pmsKnowledge.getKnowlType());
        knowl.setKnowlAmount(pmsKnowledge.getKnowlAmount());
        knowl.setKnowlDesc(pmsKnowledge.getKnowlDesc());

        return knowl;
    }
}
