package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsProject;
import com.buaa.pms.model.ProjTableItem;
import com.buaa.pms.service.PmsProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/proj")
public class PmsProjectController {
    @Resource
    PmsProjectService pmsProjectService;

    @GetMapping("/getAll")
    public List<PmsProject> getPmsProjectAll() {
        return pmsProjectService.selectAll();
    }

    @GetMapping("/getTop")
    public List<PmsProject> getPmsProjectTop() {
        return pmsProjectService.selectTop();
    }

    @GetMapping("/getPublished")
    public List<PmsProject> getPmsProjectPublished() {
        return pmsProjectService.selectPublished();
    }

    @GetMapping("/getProjTableData")
    public List<ProjTableItem> getPmsProjTableData() {
        return pmsProjectService.getProjTableData();
    }

    @GetMapping("/getByParUid/{projParUid}")
    public List<PmsProject> getPmsProjectByParUid(@PathVariable String projParUid) {
        return pmsProjectService.selectByParUid(projParUid);
    }

    @GetMapping("/getByUid/{projUid}")
    public PmsProject getPmsProjectByUid(@PathVariable String projUid) {
        return pmsProjectService.selectByUid(projUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsProject pmsProject){
        pmsProjectService.save(pmsProject);
    }

    @DeleteMapping("/deleteByUid/{projUid}")
    public void deleteByUid(@PathVariable String projUid) {
        pmsProjectService.deleteByUid(projUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsProject pmsProject) {
        pmsProjectService.update(pmsProject);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsProject pmsProject){
        pmsProjectService.saveOrUpdate(pmsProject);
    }

}
