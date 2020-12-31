package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.mapper.PmsOrganizationMapper;
import com.buaa.pms.model.OrgTableItem;
import com.buaa.pms.service.PmsOrganizationService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsOrganizationServiceImp implements PmsOrganizationService {
    @Resource
    PmsOrganizationMapper  pmsOrganizationMapper;

    @Override
    public List<PmsOrganization> selectAll() {
        return pmsOrganizationMapper.selectAll();
    }

    @Override
    public List<PmsOrganization> selectTop() {
        return pmsOrganizationMapper.selectTop();
    }

    @Override
    public List<PmsOrganization> selectByParUid(String orgParUid) {
        return pmsOrganizationMapper.selectByParUid(orgParUid);
    }

    @Override
    public PmsOrganization selectByUid(String orgUid) {
        return pmsOrganizationMapper.selectByUid(orgUid);
    }

    @Override
    public void save(PmsOrganization pmsOrganization) {
        pmsOrganization.setOrgUid(new MyUUID().getUUID());
        pmsOrganizationMapper.save(pmsOrganization);
    }

    @Override
    public void deleteByUid(String orgUid) {
        pmsOrganizationMapper.deleteByUid(orgUid);
    }

    @Override
    public void update(PmsOrganization pmsOrganization) {
        pmsOrganizationMapper.update(pmsOrganization);
    }

    @Override
    public void saveOrUpdate(PmsOrganization pmsOrganization) {
        if (pmsOrganization.getOrgUid() == null || pmsOrganization.getOrgUid().equals("")) {
            this.save(pmsOrganization);
        } else {
            this.update(pmsOrganization);
        }
    }

    @Override
    public List<OrgTableItem> getOrgTableData() {
        List<OrgTableItem> tabalData = new ArrayList<>();

        for (PmsOrganization org : pmsOrganizationMapper.selectTop()) {
            OrgTableItem tableItem = this.getTabItemFromOrg(org);
            tabalData.add(tableItem);
        }
        return tabalData;
    }

    private List<OrgTableItem> getChildrenByUid(String orgUid) {
        List<OrgTableItem> children = new ArrayList<>();
        for (PmsOrganization org : pmsOrganizationMapper.selectByParUid(orgUid)) {
            OrgTableItem tableItem = this.getTabItemFromOrg(org);
            children.add(tableItem);
        }
        return children;
    }

    private OrgTableItem getTabItemFromOrg(PmsOrganization org) {
        OrgTableItem tableItem = new OrgTableItem();
        tableItem.setOrgUid(org.getOrgUid());
        tableItem.setOrgId(org.getOrgId());
        tableItem.setOrgName(org.getOrgName());
        tableItem.setName(org.getOrgName());
        tableItem.setOrgParUid(org.getOrgParUid());
        PmsOrganization parOrg = pmsOrganizationMapper.selectByUid(org.getOrgParUid());
        tableItem.setOrgParName(parOrg == null ? "" : parOrg.getOrgName());
        tableItem.setOrgManager(org.getOrgManager());
        tableItem.setOrgPhone(org.getOrgPhone());
        tableItem.setOrgEmail(org.getOrgEmail());
        tableItem.setOrgAddress(org.getOrgAddress());
        tableItem.setOrgDescription(org.getOrgDescription());
        tableItem.setChildren(this.getChildrenByUid(org.getOrgUid()));
        return tableItem;
    }
}
