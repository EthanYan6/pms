package com.buaa.pms.service;

import com.buaa.pms.entity.PmsGroup;

import java.util.List;

public interface PmsGroupService {

    public List<PmsGroup> selectAll();

    public List<PmsGroup> selectByProjUid(String groupProjUid);

    public List<PmsGroup> selectByProcUidList(List<String> groupProcUidList);

    public List<PmsGroup> selectByProcUid(String groupProcUid);

    public PmsGroup selectByUid(String groupUid);

    public void save(PmsGroup pmsGroup);

    public void saveGroups(List<PmsGroup> pmsGroups);

    public void deleteByUid(String groupUid);

    public void deleteByTaskUid(String groupTaskUid);

    public void deleteByProjUid(String groupProjUid);

    public void deleteByProcUid(String groupProcUid);

    public void update(PmsGroup pmsGroup);

    public void updateGroups(List<PmsGroup> pmsGroups);

    public void saveOrUpdate(PmsGroup pmsGroup);

}
