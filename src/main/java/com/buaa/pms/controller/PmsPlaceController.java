package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsPlace;
import com.buaa.pms.model.Place;
import com.buaa.pms.service.PmsPlaceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("pms/place")
public class PmsPlaceController {
    @Resource
    PmsPlaceService pmsPlaceService;

    @GetMapping("/getAll")
    public List<PmsPlace> getPmsPlaceAll() {
        return pmsPlaceService.selectAll();
    }

    @GetMapping("/getPlaceList")
    public List<Place> getPlaceList() {
        return pmsPlaceService.getPlaceList();
    }

    @GetMapping("/getByOrgUid/{placeOrgUid}")
    public List<PmsPlace> getPmsPlaceByOrgUid(@PathVariable String placeOrgUid) {
        return pmsPlaceService.selectByOrgUid(placeOrgUid);
    }

    @GetMapping("/getPlaceListByOrgUid/{placeOrgUid}")
    public List<Place> getPlaceListByOrgUid(@PathVariable String placeOrgUid) {
        return pmsPlaceService.getPlaceListByOrgUid(placeOrgUid);
    }

    @GetMapping("/getByUid/{placeUid}")
    public PmsPlace getPmsPlaceByUid(@PathVariable String placeUid) {
        return pmsPlaceService.selectByUid(placeUid);
    }

    @PostMapping("/save")
    public void insert(@RequestBody PmsPlace pmsPlace){
        pmsPlaceService.save(pmsPlace);
    }

    @DeleteMapping("/deleteByUid/{placeUid}")
    public void deleteByUid(@PathVariable String placeUid) {
        pmsPlaceService.deleteByUid(placeUid);
    }

    @PutMapping("/update")
    public void update(@RequestBody PmsPlace pmsPlace) {
        pmsPlaceService.update(pmsPlace);
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody PmsPlace pmsPlace){
        pmsPlaceService.saveOrUpdate(pmsPlace);
    }

}
