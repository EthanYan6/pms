package com.buaa.pms.service;

import com.buaa.pms.entity.PmsTaskLink;

import java.util.List;

public interface PmsTaskLinkService {

    public List<PmsTaskLink> selectAll();

    public List<PmsTaskLink> selectByProjUid(String taskLinkProjUid);

    public List<PmsTaskLink> selectByProcUidList(List<String> taskLinkProcUidList);

    public List<PmsTaskLink> selectByProcUid(String taskLinkProcUid);

    public List<PmsTaskLink> selectByPreTaskUid(String taskLinkPreTaskUid);

    public List<PmsTaskLink> selectBySucTaskUid(String taskLinkSucTaskUid);

    public PmsTaskLink selectByUid(String taskLinkUid);

    public void save(PmsTaskLink pmsTaskLink);

    public void saveTaskLinks(List<PmsTaskLink> pmsTaskLinks);

    public void deleteByUid(String taskLinkUid);

    public void deleteByPreTaskUid(String taskLinkPreTaskUid);

    public void deleteBySucTaskUid(String taskLinkSucTaskUid);

    public void deleteByTaskUid(String taskLinkTaskUid);

    public void deleteByProjUid(String taskLinkProjUid);

    public void deleteByProcUid(String taskLinkProcUid);

    public void update(PmsTaskLink pmsTaskLink);

    public void updateTaskLinks(List<PmsTaskLink> pmsTaskLinks);

    public void saveOrUpdate(PmsTaskLink pmsTaskLink);

}
