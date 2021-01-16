package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsEquipment;
import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.mapper.PmsEquipmentMapper;
import com.buaa.pms.model.Equipment;
import com.buaa.pms.service.PmsEquipmentService;
import com.buaa.pms.service.PmsOrganizationService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsEquipmentServiceImp implements PmsEquipmentService {
    @Resource
    PmsEquipmentMapper  pmsEquipmentMapper;

    @Resource
    PmsOrganizationService pmsOrganizationService;

    @Override
    public List<PmsEquipment> selectAll() {
        return pmsEquipmentMapper.selectAll();
    }

    @Override
    public List<Equipment> getEquipList() {
        List<Equipment> equipList = new ArrayList<>();
        for (PmsEquipment pmsEquipment : this.selectAll()) {
            Equipment equip = this.getEquipmentFromPmsEquipment(pmsEquipment);
            equipList.add(equip);
        }
        return equipList;
    }

    @Override
    public List<PmsEquipment> selectByOrgUid(String equipOrgUid) {
        return pmsEquipmentMapper.selectByOrgUid(equipOrgUid);
    }

    @Override
    public PmsEquipment selectByUid(String equipUid) {
        return pmsEquipmentMapper.selectByUid(equipUid);
    }

    @Override
    public List<Equipment> getEquipListByOrgUid(String equipOrgUid) {
        List<Equipment> equipList = new ArrayList<>();
        for (PmsEquipment pmsEquipment : this.selectByOrgUid(equipOrgUid)) {
            Equipment equip = this.getEquipmentFromPmsEquipment(pmsEquipment);
            equipList.add(equip);
        }
        return equipList;
    }

    @Override
    public void save(PmsEquipment pmsEquipment) {
        pmsEquipment.setEquipUid(new MyUUID().getUUID());
        pmsEquipmentMapper.save(pmsEquipment);
    }

    @Override
    public void deleteByUid(String equipUid) {
        pmsEquipmentMapper.deleteByUid(equipUid);
    }

    @Override
    public void update(PmsEquipment pmsEquipment) {
        pmsEquipmentMapper.update(pmsEquipment);
    }

    @Override
    public void saveOrUpdate(PmsEquipment pmsEquipment) {
        if (pmsEquipment.getEquipUid() == null || pmsEquipment.getEquipUid().equals("")) {
            this.save(pmsEquipment);
        } else {
            this.update(pmsEquipment);
        }
    }

    private Equipment getEquipmentFromPmsEquipment(PmsEquipment pmsEquipment) {
        Equipment equip = new Equipment();
        equip.setEquipUid(pmsEquipment.getEquipUid());
        equip.setEquipId(pmsEquipment.getEquipId());
        equip.setEquipName(pmsEquipment.getEquipName());
        equip.setEquipOrgUid(pmsEquipment.getEquipOrgUid());
        PmsOrganization equipOrg = pmsOrganizationService.selectByUid(pmsEquipment.getEquipOrgUid());
        equip.setEquipOrgName(equipOrg == null ? "" : equipOrg.getOrgName());
        equip.setEquipCapType(pmsEquipment.getEquipCapType());
        equip.setEquipCapLevel(pmsEquipment.getEquipCapLevel());
        equip.setEquipCapDesc(pmsEquipment.getEquipCapDesc());

        return equip;
    }
}
