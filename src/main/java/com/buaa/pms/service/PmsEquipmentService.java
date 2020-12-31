package com.buaa.pms.service;

import com.buaa.pms.entity.PmsEquipment;
import com.buaa.pms.model.Equipment;

import java.util.List;

public interface PmsEquipmentService {

    public List<PmsEquipment> selectAll();

    public List<Equipment> getEquipList();

    public List<PmsEquipment> selectByOrgUid(String equipOrgUid);

    public PmsEquipment selectByUid(String equipUid);

    public List<Equipment> getEquipListByOrgUid(String equipOrgUid);

    public void save(PmsEquipment pmsEquipment);

    public void deleteByUid(String equipUid);

    public void update(PmsEquipment pmsEquipment);

    public void saveOrUpdate(PmsEquipment pmsEquipment);

}
