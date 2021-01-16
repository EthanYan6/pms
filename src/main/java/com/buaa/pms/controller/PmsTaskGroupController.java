package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsTaskGroup;
import com.buaa.pms.service.PmsTaskGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/taskGroup")
public class PmsTaskGroupController {
    @Resource
    PmsTaskGroupService pmsTaskGroupService;

    @GetMapping("/getAll")
    public List<PmsTaskGroup> getPmsTaskGroupAll() {
        return pmsTaskGroupService.selectAll();
    }

    @GetMapping("/getByProjUid/{taskGroupProjUid}")
    public List<PmsTaskGroup> getPmsTaskGroupByProjUid(@PathVariable String taskGroupProjUid) {
        return pmsTaskGroupService.selectByProjUid(taskGroupProjUid);
    }

    @GetMapping("/getByProcUid/{taskGroupProcUid}")
    public List<PmsTaskGroup> getPmsTaskGroupByProcUid(@PathVariable String taskGroupProcUid) {
        return pmsTaskGroupService.selectByProcUid(taskGroupProcUid);
    }

    @GetMapping("/getByGroupUid/{taskGroupGroupUid}")
    public List<PmsTaskGroup> getPmsTaskGroupByGroupUid(@PathVariable String taskGroupGroupUid) {
        return pmsTaskGroupService.selectByGroupUid(taskGroupGroupUid);
    }

    @GetMapping("/getByUid/{taskGroupUid}")
    public PmsTaskGroup getPmsTaskGroupByUid(@PathVariable String taskGroupUid) {
        return pmsTaskGroupService.selectByUid(taskGroupUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsTaskGroup pmsTaskGroup){
        pmsTaskGroupService.save(pmsTaskGroup);
    }

    @DeleteMapping("/deleteByUid/{taskGroupUid}")
    public void deleteByUid(@PathVariable String taskGroupUid) {
        pmsTaskGroupService.deleteByUid(taskGroupUid);
    }

    @DeleteMapping("/deleteByProjUid/{taskGroupProjUid}")
    public void deleteByProjUid(@PathVariable String taskGroupProjUid) {
        pmsTaskGroupService.deleteByProjUid(taskGroupProjUid);
    }

    @DeleteMapping("/deleteByProcUid/{taskGroupProcUid}")
    public void deleteByProcUid(@PathVariable String taskGroupProcUid) {
        pmsTaskGroupService.deleteByProcUid(taskGroupProcUid);
    }

    @DeleteMapping("/deleteByGroupUid/{taskGroupGroupUid}")
    public void deleteByGroupUid(@PathVariable String taskGroupGroupUid) {
        pmsTaskGroupService.deleteByGroupUid(taskGroupGroupUid);
    }

    @DeleteMapping("/deleteByTaskUid/{taskGroupTaskUid}")
    public void deleteByTaskUid(@PathVariable String taskGroupTaskUid) {
        pmsTaskGroupService.deleteByTaskUid(taskGroupTaskUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsTaskGroup pmsTaskGroup) {
        pmsTaskGroupService.update(pmsTaskGroup);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsTaskGroup pmsTaskGroup){
        pmsTaskGroupService.saveOrUpdate(pmsTaskGroup);
    }
}
