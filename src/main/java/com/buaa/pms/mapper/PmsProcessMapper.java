package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsProcess;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsProcessMapper {

    public List<PmsProcess> selectAll();

    public List<PmsProcess> selectPublished();

    public List<PmsProcess> selectByProjUid(String procProjUid);

    public List<PmsProcess> selectByUidList(List<String> procUidList);

    public PmsProcess selectByUid(String procUid);

    public void save(PmsProcess pmsProcess);

    public void deleteByUid(String procUid);

    public void deleteByProjUid(String projUid);

    public void update(PmsProcess pmsProcess);
}
