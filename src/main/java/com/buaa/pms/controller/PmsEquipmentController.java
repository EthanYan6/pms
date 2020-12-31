package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsEquipment;
import com.buaa.pms.model.Equipment;
import com.buaa.pms.model.Human;
import com.buaa.pms.service.PmsEquipmentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/equip")
public class PmsEquipmentController {
    @Resource
    PmsEquipmentService pmsEquipmentService;

    @GetMapping("/getAll")
    public List<PmsEquipment> getPmsEquipmentAll() {
        return pmsEquipmentService.selectAll();
    }

    @GetMapping("/getEquipList")
    public List<Equipment> getEquipList() {
        return pmsEquipmentService.getEquipList();
    }

    @GetMapping("/getEquipListByOrgUid/{equipOrgUid}")
    public List<Equipment> getEquipListByOrgUid(@PathVariable String equipOrgUid) {
        return pmsEquipmentService.getEquipListByOrgUid(equipOrgUid);
    }

    @GetMapping("/getByOrgUid/{equipOrgUid}")
    public List<PmsEquipment> getPmsEquipmentByOrgUid(@PathVariable String equipOrgUid) {
        return pmsEquipmentService.selectByOrgUid(equipOrgUid);
    }

    @GetMapping("/getByUid/{equipUid}")
    public PmsEquipment getPmsEquipmentByUid(@PathVariable String equipUid) {
        return pmsEquipmentService.selectByUid(equipUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsEquipment pmsEquipment){
        pmsEquipmentService.save(pmsEquipment);
    }

    @DeleteMapping("/deleteByUid/{equipUid}")
    public void deleteByUid(@PathVariable String equipUid) {
        pmsEquipmentService.deleteByUid(equipUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsEquipment pmsEquipment) {
        pmsEquipmentService.update(pmsEquipment);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsEquipment pmsEquipment){
        pmsEquipmentService.saveOrUpdate(pmsEquipment);
    }

}
