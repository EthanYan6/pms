package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsTaskResReq;
import com.buaa.pms.model.TaskResReq;
import com.buaa.pms.service.PmsTaskResReqService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/taskResReq")
public class PmsTaskResReqController {
    @Resource
    PmsTaskResReqService pmsTaskResReqService;

    @GetMapping("/getAll")
    public List<PmsTaskResReq> getPmsTaskResReqAll() {
        return pmsTaskResReqService.selectAll();
    }

    @GetMapping("/getByProjUid/{resReqProjUid}")
    public List<PmsTaskResReq> selectByProjUid(@PathVariable String resReqProjUid) {
        return pmsTaskResReqService.selectByProjUid(resReqProjUid);
    }

    @GetMapping("/getByProcUid/{resReqProcUid}")
    public List<PmsTaskResReq> selectByProcUid(@PathVariable String resReqProcUid) {
        return pmsTaskResReqService.selectByProcUid(resReqProcUid);
    }

    @GetMapping("/getByTaskUid/{resReqTaskUid}")
    public List<PmsTaskResReq> getPmsTaskResReqByTaskUid(@PathVariable String resReqTaskUid) {
        return pmsTaskResReqService.selectByTaskUid(resReqTaskUid);
    }

    @GetMapping("/getResReqListByTaskUid/{resReqTaskUid}")
    public List<TaskResReq> getResReqListByTaskUid(@PathVariable String resReqTaskUid) {
        return pmsTaskResReqService.getResReqListByTaskUid(resReqTaskUid);
    }

    @GetMapping("/getResReqListByTaskResPlanUid/{taskResPlanUid}")
    public List<TaskResReq> getResReqListByTaskResPlanUid(@PathVariable String taskResPlanUid) {
        return pmsTaskResReqService.getResReqListByTaskResPlanUid(taskResPlanUid);
    }

    @GetMapping("/getByUid/{resReqUid}")
    public PmsTaskResReq getPmsTaskResReqByUid(@PathVariable String resReqUid) {
        return pmsTaskResReqService.selectByUid(resReqUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsTaskResReq pmsTaskResReq){
        pmsTaskResReqService.save(pmsTaskResReq);
    }

    @DeleteMapping("/deleteByUid/{resReqUid}")
    public void deleteByUid(@PathVariable String resReqUid) {
        pmsTaskResReqService.deleteByUid(resReqUid);
    }

    @DeleteMapping("/deleteByProjUid/{resReqProjUid}")
    public void deleteByProjUid(@PathVariable String resReqProjUid) {
        pmsTaskResReqService.deleteByUid(resReqProjUid);
    }

    @DeleteMapping("/deleteByProcUid/{resReqProcUid}")
    public void deleteByProcUid(@PathVariable String resReqProcUid) {
        pmsTaskResReqService.deleteByUid(resReqProcUid);
    }

    @DeleteMapping("/deleteByTaskUid/{resReqTaskUid}")
    public void deleteByParUid(@PathVariable String resReqTaskUid) {
        pmsTaskResReqService.deleteByUid(resReqTaskUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsTaskResReq pmsTaskResReq) {
        pmsTaskResReqService.update(pmsTaskResReq);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsTaskResReq pmsTaskResReq){
        pmsTaskResReqService.saveOrUpdate(pmsTaskResReq);
    }

}
