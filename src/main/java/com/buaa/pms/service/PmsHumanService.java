package com.buaa.pms.service;

import com.buaa.pms.entity.PmsHuman;
import com.buaa.pms.model.Human;

import java.util.List;

public interface PmsHumanService {

    public List<PmsHuman> selectAll();

    public List<Human> getHumList();

    public List<PmsHuman> selectByOrgUid(String humOrgUid);

    public List<Human> getHumListByOrgUid(String humOrgUid);

    public PmsHuman selectByUid(String humUid);

    public void save(PmsHuman pmsHuman);

    public void deleteByUid(String humUid);

    public void update(PmsHuman pmsHuman);

    public void saveOrUpdate(PmsHuman pmsHuman);

}
