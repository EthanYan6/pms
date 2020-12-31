package com.buaa.pms.service;

import com.buaa.pms.entity.PmsKnowledge;
import com.buaa.pms.model.Knowledge;

import java.util.List;

public interface PmsKnowledgeService {

    public List<PmsKnowledge> selectAll();

    public List<Knowledge> getKnowledgeList();

    public List<PmsKnowledge> selectByOrgUid(String knowlOrgUid);

    public List<Knowledge> getKnowledgeListByOrgUid(String knowlOrgUid);

    public PmsKnowledge selectByUid(String knowlUid);

    public void save(PmsKnowledge pmsKnowledge);

    public void deleteByUid(String knowlUid);

    public void update(PmsKnowledge pmsKnowledge);

    public void saveOrUpdate(PmsKnowledge pmsKnowledge);

}
