package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsTaskGroup;
import com.buaa.pms.mapper.PmsTaskGroupMapper;
import com.buaa.pms.service.PmsTaskGroupService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsTaskGroupServiceImp implements PmsTaskGroupService {

    @Resource
    PmsTaskGroupMapper pmsTaskGroupMapper;

    @Override
    public List<PmsTaskGroup> selectAll() {
        return pmsTaskGroupMapper.selectAll();
    }

    @Override
    public List<PmsTaskGroup> selectByProjUid(String taskGroupProjUid) {
        return pmsTaskGroupMapper.selectByProjUid(taskGroupProjUid);
    }

    @Override
    public List<PmsTaskGroup> selectByProcUidList(List<String> taskGroupProcUidList) {
        if (taskGroupProcUidList != null && !taskGroupProcUidList.isEmpty())
            return pmsTaskGroupMapper.selectByProcUidList(taskGroupProcUidList);
        return null;
    }

    @Override
    public List<PmsTaskGroup> selectByProcUid(String taskGroupProcUid) {
        return pmsTaskGroupMapper.selectByProcUid(taskGroupProcUid);
    }

    @Override
    public List<PmsTaskGroup> selectByGroupUid(String taskGroupGroupUid) {
        return pmsTaskGroupMapper.selectByGroupUid(taskGroupGroupUid);
    }

    @Override
    public List<PmsTaskGroup> selectByTaskUid(String taskGroupTaskUid) {
        return pmsTaskGroupMapper.selectByTaskUid(taskGroupTaskUid);
    }

    @Override
    public PmsTaskGroup selectByUid(String taskGroupUid) {
        return pmsTaskGroupMapper.selectByUid(taskGroupUid);
    }

    @Override
    public void save(PmsTaskGroup pmsTaskGroup) {
        // 分配UUID
        pmsTaskGroup.setTaskGroupUid(new MyUUID().getUUID());
        pmsTaskGroupMapper.save(pmsTaskGroup);
    }

    @Override
    public void saveTaskGroups(List<PmsTaskGroup> pmsTaskGroups) {
        if (pmsTaskGroups != null && !pmsTaskGroups.isEmpty())
            pmsTaskGroupMapper.saveTaskGroups(pmsTaskGroups);
    }

    @Override
    public void deleteByUid(String taskGroupUid) {
        pmsTaskGroupMapper.deleteByUid(taskGroupUid);
    }

    @Override
    public void deleteByTaskUid(String taskGroupTaskUid) {
        pmsTaskGroupMapper.deleteByTaskUid(taskGroupTaskUid);
    }

    @Override
    public void deleteByProjUid(String taskGroupProjUid) {
        pmsTaskGroupMapper.deleteByProjUid(taskGroupProjUid);
    }

    @Override
    public void deleteByProcUid(String taskGroupProcUid) {
        pmsTaskGroupMapper.deleteByProcUid(taskGroupProcUid);
    }

    @Override
    public void deleteByGroupUid(String taskGroupGroupUid) {
        pmsTaskGroupMapper.deleteByGroupUid(taskGroupGroupUid);
    }

    @Override
    public void update(PmsTaskGroup pmsTaskGroup) {
        pmsTaskGroupMapper.update(pmsTaskGroup);
    }

    @Override
    public void updateTaskGroups(List<PmsTaskGroup> pmsTaskGroups) {
        if (pmsTaskGroups != null && !pmsTaskGroups.isEmpty())
            pmsTaskGroupMapper.updateTaskGroups(pmsTaskGroups);
    }

    @Override
    public void saveOrUpdate(PmsTaskGroup pmsTaskGroup) {
        if (pmsTaskGroup.getTaskGroupUid() == null || pmsTaskGroup.getTaskGroupUid().equals("")) {
            this.save(pmsTaskGroup);
        } else {
            this.update(pmsTaskGroup);
        }
    }
}
