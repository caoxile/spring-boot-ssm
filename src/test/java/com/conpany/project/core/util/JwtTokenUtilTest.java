package com.conpany.project.core.util;

import com.company.project.auth.model.User;
import com.company.project.core.util.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Package com.conpany.project.core.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-13
 */
public class JwtTokenUtilTest {


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateTokenTest(){
        String token = createToken();
        Boolean validate = JwtTokenUtil.validateToken(token);
        assertThat(validate).isEqualTo(true);
    }

    @Test
    public void getUserInfoFromTokenTest(){
        String token = createToken();
        User user = JwtTokenUtil.getUserInfoFromToken(token);
        assertThat(user).isNotNull();
        assertThat(user.getUserName()).isEqualTo("Tom James");
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
