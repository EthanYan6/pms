package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsHuman;
import com.buaa.pms.model.Human;
import com.buaa.pms.service.PmsHumanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/hum")
public class PmsHumanController {
    @Resource
    PmsHumanService pmsHumanService;

    @GetMapping("/getAll")
    public List<PmsHuman> getPmsHumanAll() {
        return pmsHumanService.selectAll();
    }

    @GetMapping("/getHumList")
    public List<Human> getHumList() {
        return pmsHumanService.getHumList();
    }

    @GetMapping("/getByOrgUid/{humOrgUid}")
    public List<PmsHuman> getPmsHumanByOrgUid(@PathVariable String humOrgUid) {
        return pmsHumanService.selectByOrgUid(humOrgUid);
    }

    @GetMapping("/getHumListByOrgUid/{humOrgUid}")
    public List<Human> getHumListByOrgUid(@PathVariable String humOrgUid) {
        return pmsHumanService.getHumListByOrgUid(humOrgUid);
    }

    @GetMapping("/getByUid/{humUid}")
    public PmsHuman getPmsHumanByUid(@PathVariable String humUid) {
        return pmsHumanService.selectByUid(humUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsHuman pmsHuman){
        pmsHumanService.save(pmsHuman);
    }

    @DeleteMapping("/deleteByUid/{humUid}")
    public void deleteByUid(@PathVariable String humUid) {
        pmsHumanService.deleteByUid(humUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsHuman pmsHuman) {
        pmsHumanService.update(pmsHuman);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsHuman pmsHuman){
        pmsHumanService.saveOrUpdate(pmsHuman);
    }

}
