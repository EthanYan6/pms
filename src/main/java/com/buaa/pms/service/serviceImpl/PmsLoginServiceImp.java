package com.buaa.pms.service.serviceImpl;

import com.buaa.pms.entity.PmsUser;
import com.buaa.pms.mapper.PmsLoginMapper;
import com.buaa.pms.service.PmsLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PmsLoginServiceImp implements PmsLoginService {

    @Resource
    PmsLoginMapper pmsLoginMapper;

    @Override
    public String pmsLogin(PmsUser pmsUser) {
        if (pmsLoginMapper.selectByNameId(pmsUser) != null)
            return "success";
        else
            return "error";
    }
}
