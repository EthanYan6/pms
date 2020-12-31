package com.buaa.pms.service.opt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptTest {
    @Resource
    OptMain optMain;

    @Test
    public void testOptMain() {
        System.out.println("test");
    }
}
