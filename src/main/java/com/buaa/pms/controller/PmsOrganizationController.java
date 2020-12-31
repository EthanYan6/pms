package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.model.OrgTableItem;
import com.buaa.pms.service.PmsOrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/org")
public class PmsOrganizationController {
    @Resource
    PmsOrganizationService pmsOrganizationService;

    @GetMapping("/getAll")
    public List<PmsOrganization> getPmsOrganizationAll() {
        return pmsOrganizationService.selectAll();
    }

    @GetMapping("/getTop")
    public List<PmsOrganization> getPmsOrganizationTop() {
        return pmsOrganizationService.selectTop();
    }

    @GetMapping("/getOrgTableData")
    public List<OrgTableItem> getPmsOrgTableData() {
        return pmsOrganizationService.getOrgTableData();
    }

    @GetMapping("/getByParUid/{orgParUid}")
    public List<PmsOrganization> getPmsOrganizationByParUid(@PathVariable String orgParUid) {
        return pmsOrganizationService.selectByParUid(orgParUid);
    }

    @GetMapping("/getByUid/{orgUid}")
    public PmsOrganization getPmsOrganizationByUid(@PathVariable String orgUid) {
        return pmsOrganizationService.selectByUid(orgUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsOrganization pmsOrganization){
        pmsOrganizationService.save(pmsOrganization);
    }

    @DeleteMapping("/deleteByUid/{orgUid}")
    public void deleteByUid(@PathVariable String orgUid) {
        pmsOrganizationService.deleteByUid(orgUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsOrganization pmsOrganization) {
        pmsOrganizationService.update(pmsOrganization);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsOrganization pmsOrganization){
        pmsOrganizationService.saveOrUpdate(pmsOrganization);
    }

}
