package com.buaa.pms.service;

import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.model.OrgTableItem;

import java.util.List;

public interface PmsOrganizationService {

    public List<PmsOrganization> selectAll();

    public List<PmsOrganization> selectTop();

    public List<PmsOrganization> selectByParUid(String orgParUid);

    public PmsOrganization selectByUid(String orgUid);

    public void save(PmsOrganization pmsOrganization);

    public void deleteByUid(String orgUid);

    public void update(PmsOrganization pmsOrganization);

    public void saveOrUpdate(PmsOrganization pmsOrganization);

    public List<OrgTableItem> getOrgTableData();
}
