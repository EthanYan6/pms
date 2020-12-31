package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsGroup;
import com.buaa.pms.mapper.PmsGroupMapper;
import com.buaa.pms.service.PmsGroupService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsGroupServiceImp implements PmsGroupService {

    @Resource
    PmsGroupMapper pmsGroupMapper;

    @Override
    public List<PmsGroup> selectAll() {
        return pmsGroupMapper.selectAll();
    }

    @Override
    public List<PmsGroup> selectByProjUid(String groupProjUid) {
        return pmsGroupMapper.selectByProjUid(groupProjUid);
    }

    @Override
    public List<PmsGroup> selectByProcUidList(List<String> groupProcUidList) {
        if (groupProcUidList != null && !groupProcUidList.isEmpty())
            return pmsGroupMapper.selectByProcUidList(groupProcUidList);
        return null;
    }

    @Override
    public List<PmsGroup> selectByProcUid(String groupProcUid) {
        return pmsGroupMapper.selectByProcUid(groupProcUid);
    }

    @Override
    public PmsGroup selectByUid(String groupUid) {
        return pmsGroupMapper.selectByUid(groupUid);
    }

    @Override
    public void save(PmsGroup pmsGroup) {
        // 分配UUID
        pmsGroup.setGroupUid(new MyUUID().getUUID());
        pmsGroupMapper.save(pmsGroup);
    }

    @Override
    public void saveGroups(List<PmsGroup> pmsGroups) {
        if (pmsGroups != null && !pmsGroups.isEmpty())
            pmsGroupMapper.saveGroups(pmsGroups);
    }

    @Override
    public void deleteByUid(String groupUid) {
        pmsGroupMapper.deleteByUid(groupUid);
    }

    @Override
    public void deleteByTaskUid(String groupTaskUid) {
        pmsGroupMapper.deleteByTaskUid(groupTaskUid);
    }

    @Override
    public void deleteByProjUid(String groupProjUid) {
        pmsGroupMapper.deleteByProjUid(groupProjUid);
    }

    @Override
    public void deleteByProcUid(String groupProcUid) {
        pmsGroupMapper.deleteByProcUid(groupProcUid);
    }

    @Override
    public void update(PmsGroup pmsGroup) {
        pmsGroupMapper.update(pmsGroup);
    }

    @Override
    public void updateGroups(List<PmsGroup> pmsGroups) {
        if (pmsGroups != null && !pmsGroups.isEmpty())
            pmsGroupMapper.updateGroups(pmsGroups);
    }

    @Override
    public void saveOrUpdate(PmsGroup pmsGroup) {
        if (pmsGroup.getGroupUid() == null || pmsGroup.getGroupUid().equals("")) {
            this.save(pmsGroup);
        } else {
            this.update(pmsGroup);
        }
    }
}
