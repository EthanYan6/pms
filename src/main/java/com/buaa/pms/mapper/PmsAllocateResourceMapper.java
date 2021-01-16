package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsAllocateResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsAllocateResourceMapper {

    public List<PmsAllocateResource> selectAll();

    public List<PmsAllocateResource> selectByProjUid(String arProjUid);

    public List<PmsAllocateResource> selectByProcUid(String arProcUid);

    public List<PmsAllocateResource> selectByTaskUid(String arTaskUid);

    public List<PmsAllocateResource> selectByResUid(String arResUid);

    public List<PmsAllocateResource> selectByResUidList(List arResUidList);

    public PmsAllocateResource selectByUid(String arUid);

    public void save(PmsAllocateResource pmsAllocateResource);

    public void deleteByUid(String arUid);

    public void deleteByTaskUid(String arTaskUid);

    public void deleteByProjUid(String arProjUid);

    public void deleteByProcUid(String arProcUid);

    public void update(PmsAllocateResource pmsAllocateResource);
}
