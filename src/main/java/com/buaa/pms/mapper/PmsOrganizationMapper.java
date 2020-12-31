package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsOrganization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsOrganizationMapper {

    public List<PmsOrganization> selectAll();

    public List<PmsOrganization> selectTop();

    public List<PmsOrganization> selectByParUid(String orgParUid);

    public PmsOrganization selectByUid(String orgUid);

    public void save(PmsOrganization pmsOrganization);

    public void deleteByUid(String orgUid);

    public void update(PmsOrganization pmsOrganization);

}
