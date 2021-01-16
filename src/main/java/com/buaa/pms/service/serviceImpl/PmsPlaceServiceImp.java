package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.entity.PmsPlace;
import com.buaa.pms.mapper.PmsPlaceMapper;
import com.buaa.pms.model.Place;
import com.buaa.pms.service.PmsOrganizationService;
import com.buaa.pms.service.PmsPlaceService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsPlaceServiceImp implements PmsPlaceService {
    @Resource
    PmsPlaceMapper  pmsPlaceMapper;

    @Resource
    PmsOrganizationService pmsOrganizationService;

    @Override
    public List<PmsPlace> selectAll() {
        return pmsPlaceMapper.selectAll();
    }

    @Override
    public List<Place> getPlaceList() {
        List<Place> placeList = new ArrayList<>();
        for (PmsPlace pmsPlace : this.selectAll()) {
            Place place = this.getPlaceFromPmsPlace(pmsPlace);
            placeList.add(place);
        }
        return placeList;
    }

    @Override
    public List<PmsPlace> selectByOrgUid(String placeOrgUid) {
        return pmsPlaceMapper.selectByOrgUid(placeOrgUid);
    }

    @Override
    public List<Place> getPlaceListByOrgUid(String placeOrgUid) {
        List<Place> placeList = new ArrayList<>();
        for (PmsPlace pmsPlace : this.selectByOrgUid(placeOrgUid)) {
            Place place = this.getPlaceFromPmsPlace(pmsPlace);
            placeList.add(place);
        }
        return placeList;
    }

    @Override
    public PmsPlace selectByUid(String placeUid) {
        return pmsPlaceMapper.selectByUid(placeUid);
    }

    @Override
    public void save(PmsPlace pmsPlace) {
        pmsPlace.setPlaceUid(new MyUUID().getUUID());
        pmsPlaceMapper.save(pmsPlace);
    }

    @Override
    public void deleteByUid(String placeUid) {
        pmsPlaceMapper.deleteByUid(placeUid);
    }

    @Override
    public void update(PmsPlace pmsPlace) {
        pmsPlaceMapper.update(pmsPlace);
    }

    @Override
    public void saveOrUpdate(PmsPlace pmsPlace) {
        if (pmsPlace.getPlaceUid() == null || pmsPlace.getPlaceUid().equals("")) {
            this.save(pmsPlace);
        } else {
            this.update(pmsPlace);
        }
    }

    private Place getPlaceFromPmsPlace(PmsPlace pmsPlace) {
        Place place = new Place();
        place.setPlaceUid(pmsPlace.getPlaceUid());
        place.setPlaceId(pmsPlace.getPlaceId());
        place.setPlaceName(pmsPlace.getPlaceName());
        place.setPlaceOrgUid(pmsPlace.getPlaceOrgUid());
        PmsOrganization placeOrg = pmsOrganizationService.selectByUid(pmsPlace.getPlaceOrgUid());
        place.setPlaceOrgName(placeOrg == null ? "" : placeOrg.getOrgName());
        place.setPlaceType(pmsPlace.getPlaceType());
        place.setPlaceArea(pmsPlace.getPlaceArea());
        place.setPlaceDesc(pmsPlace.getPlaceDesc());

        return place;
    }
}
