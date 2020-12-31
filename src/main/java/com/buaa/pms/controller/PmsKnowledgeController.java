package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsKnowledge;
import com.buaa.pms.model.Knowledge;
import com.buaa.pms.service.PmsKnowledgeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/knowl")
public class PmsKnowledgeController {
    @Resource
    PmsKnowledgeService pmsKnowledgeService;

    @GetMapping("/getAll")
    public List<PmsKnowledge> getPmsKnowledgeAll() {
        return pmsKnowledgeService.selectAll();
    }

    @GetMapping("/getKnowledgeList")
    public List<Knowledge> getKnowledgeList() {
        return pmsKnowledgeService.getKnowledgeList();
    }

    @GetMapping("/getByOrgUid/{knowlOrgUid}")
    public List<PmsKnowledge> getPmsKnowledgeByOrgUid(@PathVariable String knowlOrgUid) {
        return pmsKnowledgeService.selectByOrgUid(knowlOrgUid);
    }

    @GetMapping("/getKnowledgeListByOrgUid/{knowlOrgUid}")
    public List<Knowledge> getKnowledgeListByOrgUid(@PathVariable String knowlOrgUid) {
        return pmsKnowledgeService.getKnowledgeListByOrgUid(knowlOrgUid);
    }

    @GetMapping("/getByUid/{knowlUid}")
    public PmsKnowledge getPmsKnowledgeByUid(@PathVariable String knowlUid) {
        return pmsKnowledgeService.selectByUid(knowlUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsKnowledge pmsKnowledge){
        pmsKnowledgeService.save(pmsKnowledge);
    }

    @DeleteMapping("/deleteByUid/{knowlUid}")
    public void deleteByUid(@PathVariable String knowlUid) {
        pmsKnowledgeService.deleteByUid(knowlUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsKnowledge pmsKnowledge) {
        pmsKnowledgeService.update(pmsKnowledge);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsKnowledge pmsKnowledge){
        pmsKnowledgeService.saveOrUpdate(pmsKnowledge);
    }

}
