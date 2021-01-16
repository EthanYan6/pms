package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsTaskResPlan;
import com.buaa.pms.service.PmsTaskResPlanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/taskResPlan")
public class PmsTaskResPlanController {
    @Resource
    PmsTaskResPlanService pmsTaskResPlanService;

    @GetMapping("/getAll")
    public List<PmsTaskResPlan> getPmsTaskResPlanAll() {
        return pmsTaskResPlanService.selectAll();
    }

    @GetMapping("/getByProjUid/{resPlanProjUid}")
    public List<PmsTaskResPlan> selectByProjUid(@PathVariable String resPlanProjUid) {
        return pmsTaskResPlanService.selectByProjUid(resPlanProjUid);
    }

    @GetMapping("/getByProcUid/{resPlanProcUid}")
    public List<PmsTaskResPlan> selectByProcUid(@PathVariable String resPlanProcUid) {
        return pmsTaskResPlanService.selectByProcUid(resPlanProcUid);
    }

    @GetMapping("/getByTaskUid/{resPlanTaskUid}")
    public List<PmsTaskResPlan> getPmsTaskResPlanByParUid(@PathVariable String resPlanTaskUid) {
        return pmsTaskResPlanService.selectByTaskUid(resPlanTaskUid);
    }

    @GetMapping("/getByUid/{resPlanUid}")
    public PmsTaskResPlan getPmsTaskResPlanByUid(@PathVariable String resPlanUid) {
        return pmsTaskResPlanService.selectByUid(resPlanUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsTaskResPlan pmsTaskResPlan){
        pmsTaskResPlanService.save(pmsTaskResPlan);
    }

    @DeleteMapping("/deleteByUid/{resPlanUid}")
    public void deleteByUid(@PathVariable String resPlanUid) {
        pmsTaskResPlanService.deleteByUid(resPlanUid);
    }

    @DeleteMapping("/deleteByProjUid/{resPlanProjUid}")
    public void deleteByProjUid(@PathVariable String resPlanProjUid) {
        pmsTaskResPlanService.deleteByUid(resPlanProjUid);
    }

    @DeleteMapping("/deleteByProcUid/{resPlanProcUid}")
    public void deleteByProcUid(@PathVariable String resPlanProcUid) {
        pmsTaskResPlanService.deleteByUid(resPlanProcUid);
    }

    @DeleteMapping("/deleteByTaskUid/{resPlanTaskUid}")
    public void deleteByParUid(@PathVariable String resPlanTaskUid) {
        pmsTaskResPlanService.deleteByUid(resPlanTaskUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsTaskResPlan pmsTaskResPlan) {
        pmsTaskResPlanService.update(pmsTaskResPlan);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsTaskResPlan pmsTaskResPlan){
        pmsTaskResPlanService.saveOrUpdate(pmsTaskResPlan);
    }

}
