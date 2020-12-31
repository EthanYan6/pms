package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsGroup;
import com.buaa.pms.service.PmsGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/group")
public class PmsGroupController {
    @Resource
    PmsGroupService pmsGroupService;

    @GetMapping("/getAll")
    public List<PmsGroup> getPmsGroupAll() {
        return pmsGroupService.selectAll();
    }

    @GetMapping("/getByProjUid/{groupProjUid}")
    public List<PmsGroup> getPmsGroupByProjUid(@PathVariable String groupProjUid) {
        return pmsGroupService.selectByProjUid(groupProjUid);
    }

    @GetMapping("/getByProcUid/{groupProcUid}")
    public List<PmsGroup> getPmsGroupByProcUid(@PathVariable String groupProcUid) {
        return pmsGroupService.selectByProcUid(groupProcUid);
    }

    @GetMapping("/getByUid/{groupUid}")
    public PmsGroup getPmsGroupByUid(@PathVariable String groupUid) {
        return pmsGroupService.selectByUid(groupUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsGroup pmsGroup){
        pmsGroupService.save(pmsGroup);
    }

    @DeleteMapping("/deleteByUid/{groupUid}")
    public void deleteByUid(@PathVariable String groupUid) {
        pmsGroupService.deleteByUid(groupUid);
    }

    @DeleteMapping("/deleteByProjUid/{groupProjUid}")
    public void deleteByProjUid(@PathVariable String groupProjUid) {
        pmsGroupService.deleteByProjUid(groupProjUid);
    }

    @DeleteMapping("/deleteByProcUid/{groupProcUid}")
    public void deleteByProcUid(@PathVariable String groupProcUid) {
        pmsGroupService.deleteByProcUid(groupProcUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsGroup pmsGroup) {
        pmsGroupService.update(pmsGroup);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsGroup pmsGroup){
        pmsGroupService.saveOrUpdate(pmsGroup);
    }
}
