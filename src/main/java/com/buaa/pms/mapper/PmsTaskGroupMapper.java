package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsTaskGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsTaskGroupMapper {

    public List<PmsTaskGroup> selectAll();

    public List<PmsTaskGroup> selectByProjUid(String taskGroupProjUid);

    public List<PmsTaskGroup> selectByProcUidList(List<String> taskGroupProcUidList);

    public List<PmsTaskGroup> selectByProcUid(String taskGroupProcUid);

    public List<PmsTaskGroup> selectByGroupUid(String taskGroupGroupUid);

    public List<PmsTaskGroup> selectByTaskUid(String taskGroupTaskUid);

    public PmsTaskGroup selectByUid(String taskGroupUid);

    public void save(PmsTaskGroup pmsTaskGroup);

    public void saveTaskGroups(List<PmsTaskGroup> pmsTaskGroups);

    public void deleteByUid(String taskGroupUid);

    public void deleteByTaskUid(String taskGroupTaskUid);

    public void deleteByProjUid(String taskGroupProjUid);

    public void deleteByProcUid(String taskGroupProcUid);

    public void deleteByGroupUid(String taskGroupGroupUid);

    public void update(PmsTaskGroup pmsTaskGroup);

    public void updateTaskGroups(List<PmsTaskGroup> pmsTaskGroups);
}
