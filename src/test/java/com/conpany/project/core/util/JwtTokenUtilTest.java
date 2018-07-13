package com.conpany.project.core.util;

import com.company.project.core.util.JwtTokenUtil;
import com.company.project.core.util.JwtUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private JwtTokenUtil jwtTokenUtil;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateTokenTest(){
        String token = createToken();
        Boolean validate = jwtTokenUtil.validateToken(token);
        assertThat(validate).isEqualTo(true);
    }

    @Test
    public void getUserInfoFromTokenTest(){
        String token = createToken();
        JwtUser jwtUser = jwtTokenUtil.getUserInfoFromToken(token);
        assertThat(jwtUser).isNotNull();
        assertThat(jwtUser.getUsername()).isEqualTo("caoxile");
    }

    @Test
    public void refreshTokenTest(){
        String token = createToken();
        jwtTokenUtil.refreshToken(token);
    }

    private String createToken() {
        JwtUser jwtUser = new JwtUser(1L,"caoxile","caoxile@126.com",true);
        return jwtTokenUtil.generateToken(jwtUser);
    }
}
