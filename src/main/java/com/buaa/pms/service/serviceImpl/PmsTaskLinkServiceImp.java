package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsTaskLink;
import com.buaa.pms.mapper.PmsTaskLinkMapper;
import com.buaa.pms.service.PmsTaskLinkService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PmsTaskLinkServiceImp implements PmsTaskLinkService {

    @Resource
    PmsTaskLinkMapper pmsTaskLinkMapper;

    @Override
    public List<PmsTaskLink> selectAll() {
        return pmsTaskLinkMapper.selectAll();
    }

    @Override
    public List<PmsTaskLink> selectByProjUid(String taskLinkProjUid) {
        return pmsTaskLinkMapper.selectByProjUid(taskLinkProjUid);
    }

    @Override
    public List<PmsTaskLink> selectByProcUidList(List<String> taskLinkProcUidList) {
        if (taskLinkProcUidList != null && !taskLinkProcUidList.isEmpty())
            return pmsTaskLinkMapper.selectByProcUidList(taskLinkProcUidList);
        return null;
    }

    @Override
    public List<PmsTaskLink> selectByProcUid(String taskLinkProcUid) {
        return pmsTaskLinkMapper.selectByProcUid(taskLinkProcUid);
    }

    @Override
    public List<PmsTaskLink> selectByPreTaskUid(String taskLinkPreTaskUid) {
        return pmsTaskLinkMapper.selectByPreTaskUid(taskLinkPreTaskUid);
    }

    @Override
    public List<PmsTaskLink> selectBySucTaskUid(String taskLinkSucTaskUid) {
        return pmsTaskLinkMapper.selectBySucTaskUid(taskLinkSucTaskUid);
    }

    @Override
    public PmsTaskLink selectByUid(String taskLinkUid) {
        return pmsTaskLinkMapper.selectByUid(taskLinkUid);
    }

    @Override
    public void save(PmsTaskLink pmsTaskLink) {
        // 分配UUID
        pmsTaskLink.setTaskLinkUid(new MyUUID().getUUID());
        pmsTaskLinkMapper.save(pmsTaskLink);
    }

    @Override
    public void saveTaskLinks(List<PmsTaskLink> pmsTaskLinks) {
        if (pmsTaskLinks != null && !pmsTaskLinks.isEmpty())
            pmsTaskLinkMapper.saveTaskLinks(pmsTaskLinks);
    }

    @Override
    public void deleteByUid(String taskLinkUid) {
        pmsTaskLinkMapper.deleteByUid(taskLinkUid);
    }

    @Override
    public void deleteByPreTaskUid(String taskLinkPreTaskUid) {
        pmsTaskLinkMapper.deleteByPreTaskUid(taskLinkPreTaskUid);
    }

    @Override
    public void deleteBySucTaskUid(String taskLinkSucTaskUid) {
        pmsTaskLinkMapper.deleteBySucTaskUid(taskLinkSucTaskUid);
    }

    @Override
    public void deleteByTaskUid(String taskLinkTaskUid) {
        pmsTaskLinkMapper.deleteByTaskUid(taskLinkTaskUid);
    }

    @Override
    public void deleteByProjUid(String taskLinkProjUid) {
        pmsTaskLinkMapper.deleteByProjUid(taskLinkProjUid);
    }

    @Override
    public void deleteByProcUid(String taskLinkProcUid) {
        pmsTaskLinkMapper.deleteByProcUid(taskLinkProcUid);
    }

    @Override
    public void update(PmsTaskLink pmsTaskLink) {
        pmsTaskLinkMapper.update(pmsTaskLink);
    }

    @Override
    public void updateTaskLinks(List<PmsTaskLink> pmsTaskLinks) {
        if (pmsTaskLinks != null && !pmsTaskLinks.isEmpty())
            pmsTaskLinkMapper.updateTaskLinks(pmsTaskLinks);
    }

    @Override
    public void saveOrUpdate(PmsTaskLink pmsTaskLink) {
        if (pmsTaskLink.getTaskLinkUid() == null || pmsTaskLink.getTaskLinkUid().equals("")) {
            this.save(pmsTaskLink);
        } else {
            this.update(pmsTaskLink);
        }
    }
}
