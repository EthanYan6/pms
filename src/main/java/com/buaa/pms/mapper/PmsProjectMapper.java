package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsProject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsProjectMapper {

    public List<PmsProject> selectAll();

    public List<PmsProject> selectTop();

    public List<PmsProject> selectPublished();

    public List<PmsProject> selectByParUid(String projParUid);

    public List<PmsProject> selectByUidList(List<String> projUidList);

    public PmsProject selectByUid(String projUid);

    public void save(PmsProject pmsProject);

    public void deleteByUid(String projUid);

    public void update(PmsProject pmsProject);
}
