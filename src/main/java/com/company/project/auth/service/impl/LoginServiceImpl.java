package com.company.project.auth.service.impl;

import com.company.project.auth.model.User;
import com.company.project.auth.service.LoginService;
import com.company.project.auth.service.PermissionService;
import com.company.project.auth.service.RoleService;
import com.company.project.common.core.Constants;
import com.company.project.common.core.ServiceException;
import com.company.project.common.util.SHA256Util;
import com.company.project.common.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Set;


/**
 * @Package com.company.project.auth.service.impl
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-24
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService{

    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    public void login(String username, String password) {
        Assert.isTrue(!StringUtil.isNullOrEmpty(username),"用户名为空");
        Assert.isTrue(!StringUtil.isNullOrEmpty(password),"密码为空");
        Subject currentUser = SecurityUtils.getSubject();
        password = SHA256Util.encrypt(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            throw new ServiceException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new ServiceException("认证失败");
        }
    }

    /**
     * 在getInfo的时候，重新获取权限，并且更新session
     * @return
     */
    @Override
    public User getInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Set<String> userStringPermissions = permissionService.findUserStringPermissions(user.getId());
        user.setPermissions(userStringPermissions);
        user.setRoles(roleService.findUserStringRoles(user.getId()));
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userStringPermissions);
        return user;
    }

    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

}
