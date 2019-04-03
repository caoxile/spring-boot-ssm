package com.company.project.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.company.project.auth.service.LoginService;
import com.company.project.core.Constants;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.util.SHA256Util;
import com.company.project.core.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Override
    public Result login(String username, String password) throws Exception {
        Assert.isTrue(!StringUtil.isNullOrEmpty(username),"用户名为空");
        Assert.isTrue(!StringUtil.isNullOrEmpty(password),"密码为空");
        Subject currentUser = SecurityUtils.getSubject();
        password = SHA256Util.encrypt(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return ResultGenerator.genSuccessResult();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResultGenerator.genFailResult(e.getMessage());
        } catch (AuthenticationException e) {
            return ResultGenerator.genFailResult("认证失败!");
        }
    }

    @Override
    public Result getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
//        JSONObject userPermission = permissionService.getUserPermission(username);
        JSONObject userPermission = null;
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        userInfo.put("userPermission", userPermission);
        return ResultGenerator.genSuccessResult(userInfo);
    }

    @Override
    public Result logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
//        try {
//            Subject currentUser = SecurityUtils.getSubject();
//            currentUser.logout();
//        } catch (Exception e) {
//        }
        return ResultGenerator.genSuccessResult();
    }

}
