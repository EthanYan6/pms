package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsPlace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsPlaceMapper {

    public List<PmsPlace> selectAll();

    public List<PmsPlace> selectByOrgUid(String placeOrgUid);

    public PmsPlace selectByUid(String placeUid);

    public void save(PmsPlace pmsPlace);

    public void deleteByUid(String placeUid);

    public void update(PmsPlace pmsPlace);

}
