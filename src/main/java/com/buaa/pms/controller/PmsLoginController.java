package com.buaa.pms.controller;

import com.buaa.pms.entity.PmsUser;
import com.buaa.pms.service.PmsLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pms")
public class PmsLoginController {

    @Resource
    PmsLoginService pmsLoginService;

    @PostMapping("/login")
    public String Login(@RequestBody PmsUser pmsUser) {
        return pmsLoginService.pmsLogin(pmsUser);
    }
}
