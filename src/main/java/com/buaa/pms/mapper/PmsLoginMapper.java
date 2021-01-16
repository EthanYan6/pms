package com.buaa.pms.mapper;

import com.buaa.pms.entity.PmsUser;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsLoginMapper {

    public PmsUser selectByNameId(PmsUser pmsUser);
}
