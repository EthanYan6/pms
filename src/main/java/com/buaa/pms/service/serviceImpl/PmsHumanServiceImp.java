package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsHuman;
import com.buaa.pms.entity.PmsOrganization;
import com.buaa.pms.mapper.PmsHumanMapper;
import com.buaa.pms.model.Human;
import com.buaa.pms.service.PmsHumanService;
import com.buaa.pms.service.PmsOrganizationService;
import com.buaa.pms.util.MyUUID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PmsHumanServiceImp implements PmsHumanService {
    @Resource
    PmsHumanMapper  pmsHumanMapper;

    @Resource
    PmsOrganizationService pmsOrganizationService;

    @Override
    public List<PmsHuman> selectAll() {
        return pmsHumanMapper.selectAll();
    }

    @Override
    public List<Human> getHumList() {
        List<Human> humanList = new ArrayList<>();
        for (PmsHuman pmsHuman : this.selectAll()) {
            Human human = this.getHumanFromPmsHuman(pmsHuman);
            humanList.add(human);
        }
        return humanList;
    }

    @Override
    public List<PmsHuman> selectByOrgUid(String humOrgUid) {
        return pmsHumanMapper.selectByOrgUid(humOrgUid);
    }

    @Override
    public List<Human> getHumListByOrgUid(String humOrgUid) {
        List<Human> humList = new ArrayList<>();
        for (PmsHuman pmsHuman : this.selectByOrgUid(humOrgUid)) {
            Human human = this.getHumanFromPmsHuman(pmsHuman);
            humList.add(human);
        }
        return humList;
    }

    @Override
    public PmsHuman selectByUid(String humUid) {
        return pmsHumanMapper.selectByUid(humUid);
    }

    @Override
    public void save(PmsHuman pmsHuman) {
        pmsHuman.setHumUid(new MyUUID().getUUID());
        pmsHumanMapper.save(pmsHuman);
    }

    @Override
    public void deleteByUid(String humUid) {
        pmsHumanMapper.deleteByUid(humUid);
    }

    @Override
    public void update(PmsHuman pmsHuman) {
        pmsHumanMapper.update(pmsHuman);
    }

    @Override
    public void saveOrUpdate(PmsHuman pmsHuman) {
        if (pmsHuman.getHumUid() == null || pmsHuman.getHumUid().equals("")) {
            this.save(pmsHuman);
        } else {
            this.update(pmsHuman);
        }
    }

    private Human getHumanFromPmsHuman(PmsHuman pmsHuman) {
        Human human = new Human();
        human.setHumUid(pmsHuman.getHumUid());
        human.setHumId(pmsHuman.getHumId());
        human.setHumName(pmsHuman.getHumName());
        human.setHumOrgUid(pmsHuman.getHumOrgUid());
        PmsOrganization humOrg = pmsOrganizationService.selectByUid(pmsHuman.getHumOrgUid());
        human.setHumOrgName(humOrg == null ? "" : humOrg.getOrgName());
        human.setHumPosition(pmsHuman.getHumPosition());
        human.setHumAbilityType(pmsHuman.getHumAbilityType());
        human.setHumAbilityLevel(pmsHuman.getHumAbilityLevel());
        human.setHumAbilityDesc(pmsHuman.getHumAbilityDesc());
        human.setHumPhone(pmsHuman.getHumPhone());
        human.setHumEmail(pmsHuman.getHumEmail());

        return human;
    }
}
