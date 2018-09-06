package com.conpany.project.core.util;

import com.company.project.Application;
import com.company.project.core.util.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Package com.company.project.core.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisUtilTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void setTest() {
        redisUtil.set("hello", "redis");
        Assert.assertEquals("redis", redisUtil.get("hello"));
    }

}
