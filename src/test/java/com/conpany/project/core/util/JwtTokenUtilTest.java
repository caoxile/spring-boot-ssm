package com.conpany.project.core.util;

import com.company.project.auth.model.User;
import com.company.project.core.util.JwtTokenUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Package com.company.project.core.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-13
 */
public class JwtTokenUtilTest {


    @Before
    public void init() {
        System.out.println("Before test....");
    }

    @Test
    public void validateTokenTest(){
        String token = createToken();
        Boolean validate = JwtTokenUtil.validateToken(token);
        Assert.assertTrue(validate);
    }

    @Test
    public void getUserInfoFromTokenTest(){
        String token = createToken();
        User user = JwtTokenUtil.getUserInfoFromToken(token);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUserName(),"Tom James");
    }

    @Test
    public void refreshTokenTest(){
        String token = createToken();
        JwtTokenUtil.refreshToken(token);
    }

    private String createToken() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Tom James");
        user.setLoginName("Tom");
        user.setEmail("caoxile@126.com");
        return JwtTokenUtil.generateToken(user);
    }
}
