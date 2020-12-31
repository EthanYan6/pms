package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsKnowledge;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsKnowledgeMapper {

    public List<PmsKnowledge> selectAll();

    public List<PmsKnowledge> selectByOrgUid(String knowlOrgUid);

    public PmsKnowledge selectByUid(String knowlUid);

    public void save(PmsKnowledge pmsKnowledge);

    public void deleteByUid(String knowlUid);

    public void update(PmsKnowledge pmsKnowledge);

}
