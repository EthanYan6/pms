package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsProject;
import com.buaa.pms.entity.PmsTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsTaskMapper {

    public List<PmsTask> selectAll();

    public List<PmsTask> selectByProjUid(String taskProjUid);

    public List<PmsTask> selectByProcUidList(List<String> taskProcUidList);

    public List<PmsTask> selectByProcUid(String taskProcUid);

    public List<PmsTask> selectByParUid(String taskParUid);

    public List<PmsTask> selectByUidList(List taskUidList);

    public PmsTask selectByUid(String taskUid);

    public void save(PmsTask pmsTask);

    public void deleteByUid(String taskUid);

    public void deleteByProjUid(String taskProjUid);

    public void deleteByProcUid(String taskProcUid);

    public void deleteByParUid(String taskParUid);

    public void update(PmsTask pmsTask);

    public void updatePriorityByProj(PmsProject pmsProject);

    public void updatePmsTaskIds(List<PmsTask> pmsTasks);

    public void updatePmsTasks(List<PmsTask> pmsTasks);
}
