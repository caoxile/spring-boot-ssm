package com.company.project.auth.service.impl;

import com.company.project.auth.model.User;
import com.company.project.auth.service.LoginService;
import com.company.project.auth.service.UserService;
import com.company.project.core.ServiceException;
import com.company.project.core.util.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


/**
 * @Package com.company.project.auth.service.impl
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-24
 */
@Service
public class LoginServiceImpl implements LoginService{

    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public User login(String loginName, String password) {
        Assert.isTrue(!StringUtil.isNullOrEmpty(loginName),"登录名为空");
        Assert.isTrue(!StringUtil.isNullOrEmpty(loginName),"密码为空");
        User user = userService.findBy("loginName",loginName);
        if(user==null){
            logger.info("帐号不存在:"+loginName);
            throw new ServiceException("帐号不存在");
        }
        //这里是密码的散列算法(SHA256)
        String hash256Password = DigestUtils.sha256Hex(password);
        if(!user.getPassword().equals(hash256Password)){
            logger.info("用户名密码错误");
            throw new ServiceException("用户名密码错误");
        }
        user.setPassword(null);
        return user;
    }

}
