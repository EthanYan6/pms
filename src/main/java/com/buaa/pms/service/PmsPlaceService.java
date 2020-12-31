package com.buaa.pms.service;

import com.buaa.pms.entity.PmsPlace;
import com.buaa.pms.model.Place;

import java.util.List;

public interface PmsPlaceService {

    public List<PmsPlace> selectAll();

    public List<Place> getPlaceList();

    public List<PmsPlace> selectByOrgUid(String placeOrgUid);

    public List<Place> getPlaceListByOrgUid(String placeOrgUid);

    public PmsPlace selectByUid(String placeUid);

    public void save(PmsPlace pmsPlace);

    public void deleteByUid(String placeUid);

    public void update(PmsPlace pmsPlace);

    public void saveOrUpdate(PmsPlace pmsPlace);

}
