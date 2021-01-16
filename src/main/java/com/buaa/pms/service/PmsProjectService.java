package com.buaa.pms.service;

import com.buaa.pms.entity.PmsProject;
import com.buaa.pms.model.ProjTableItem;

import java.util.List;

public interface PmsProjectService {

    public List<PmsProject> selectAll();

    public List<PmsProject> selectTop();

    public List<PmsProject> selectPublished();

    public List<PmsProject> selectByParUid(String projParUid);

    public List<PmsProject> selectByUidList(List<String> projUidList);

    public PmsProject selectByUid(String projUid);

    public void save(PmsProject pmsProject);

    public void deleteByUid(String projUid);

    public void update(PmsProject pmsProject);

    public void saveOrUpdate(PmsProject pmsProject);

    public List<ProjTableItem> getProjTableData();

}
