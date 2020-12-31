package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsEquipment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsEquipmentMapper {

    public List<PmsEquipment> selectAll();

    public List<PmsEquipment> selectByOrgUid(String equipOrgUid);

    public PmsEquipment selectByUid(String equipUid);

    public void save(PmsEquipment pmsEquipment);

    public void deleteByUid(String equipUid);

    public void update(PmsEquipment pmsEquipment);

}
