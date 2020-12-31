package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsHuman;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PmsHumanMapper {

    public List<PmsHuman> selectAll();

    public List<PmsHuman> selectByOrgUid(String humOrgUid);

    public PmsHuman selectByUid(String humUid);

    public void save(PmsHuman pmsHuman);

    public void deleteByUid(String humUid);

    public void update(PmsHuman pmsHuman);

}
