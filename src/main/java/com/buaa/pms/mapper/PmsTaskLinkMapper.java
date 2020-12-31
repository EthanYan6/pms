package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsTaskLink;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsTaskLinkMapper {

    public List<PmsTaskLink> selectAll();

    public List<PmsTaskLink> selectByProjUid(String taskLinkProjUid);

    public List<PmsTaskLink> selectByProcUidList(List<String> taskLinkProcUidList);

    public List<PmsTaskLink> selectByProcUid(String taskLinkProcUid);

    public List<PmsTaskLink> selectByTaskUid(String taskLinkTaskUid);

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
}
