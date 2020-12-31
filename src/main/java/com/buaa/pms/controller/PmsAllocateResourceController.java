package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsAllocateResource;
import com.buaa.pms.model.AllocateResource;
import com.buaa.pms.model.ChartAllocateResource;
import com.buaa.pms.model.ResUidsResType;
import com.buaa.pms.service.PmsAllocateResourceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pms/ar")
public class PmsAllocateResourceController {
    @Resource
    PmsAllocateResourceService pmsAllocateResourceService;

    @GetMapping("/getAll")
    public List<PmsAllocateResource> getPmsAllocateResourceAll() {
        return pmsAllocateResourceService.selectAll();
    }

    @GetMapping("/getByProjUid/{arProjUid}")
    public List<PmsAllocateResource> selectByProjUid(@PathVariable String arProjUid) {
        return pmsAllocateResourceService.selectByProjUid(arProjUid);
    }

    @GetMapping("/getByProcUid/{arProcUid}")
    public List<PmsAllocateResource> selectByProcUid(@PathVariable String arProcUid) {
        return pmsAllocateResourceService.selectByProcUid(arProcUid);
    }

    @GetMapping("/getByTaskUid/{arTaskUid}")
    public List<PmsAllocateResource> getPmsAllocateResourceByTaskUid(@PathVariable String arTaskUid) {
        return pmsAllocateResourceService.selectByTaskUid(arTaskUid);
    }

    @GetMapping("/getAllocateResourceListByResUid/{arResUid}")
    public List<AllocateResource> getAllocateResourceListByResUid(@PathVariable String arResUid) {
        return pmsAllocateResourceService.getAllocateResourceListByResUid(arResUid);
    }

    @PostMapping("/getChartAllocateResourceListByResUids")
    public List<ChartAllocateResource> getEchartsAllocateResourceListByResUids(@RequestBody ResUidsResType resUidsResType){
        return pmsAllocateResourceService.getChartAllocateResourceListByResUids(resUidsResType.getResUids(), resUidsResType.getResType());
    }

    @GetMapping("/getByUid/{arUid}")
    public PmsAllocateResource getPmsAllocateResourceByUid(@PathVariable String arUid) {
        return pmsAllocateResourceService.selectByUid(arUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsAllocateResource pmsAllocateResource){
        pmsAllocateResourceService.save(pmsAllocateResource);
    }

    @DeleteMapping("/deleteByUid/{arUid}")
    public void deleteByUid(@PathVariable String arUid) {
        pmsAllocateResourceService.deleteByUid(arUid);
    }

    @DeleteMapping("/deleteByProjUid/{arProjUid}")
    public void deleteByProjUid(@PathVariable String arProjUid) {
        pmsAllocateResourceService.deleteByUid(arProjUid);
    }

    @DeleteMapping("/deleteByProcUid/{arProcUid}")
    public void deleteByProcUid(@PathVariable String arProcUid) {
        pmsAllocateResourceService.deleteByUid(arProcUid);
    }

    @DeleteMapping("/deleteByTaskUid/{arTaskUid}")
    public void deleteByParUid(@PathVariable String arTaskUid) {
        pmsAllocateResourceService.deleteByUid(arTaskUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsAllocateResource pmsAllocateResource) {
        pmsAllocateResourceService.update(pmsAllocateResource);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsAllocateResource pmsAllocateResource){
        pmsAllocateResourceService.saveOrUpdate(pmsAllocateResource);
    }

}
