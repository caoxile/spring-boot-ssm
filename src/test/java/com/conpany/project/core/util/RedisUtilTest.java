package com.conpany.project.core.util;

import com.company.project.Application;
import com.company.project.common.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.company.project.common.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisUtilTest {

    @Autowired
    RedisService redisService;

    @Test
    public void setTest() {
        redisService.set("hello", "redis");
        Assert.assertEquals("redis", redisService.get("hello"));
    }

}
