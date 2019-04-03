package com.company.project.shiro;

import com.alibaba.fastjson.JSONObject;
import com.company.project.auth.constants.AuthConstants;
import com.company.project.auth.model.User;
import com.company.project.auth.service.UserService;
import com.company.project.core.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author: caoxile
 * @description: 自定义Realm
 * @date: 2019/04/01
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	@Override
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		//查询用户的权限
		JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
		logger.info("permission的值为:" + permission);
		logger.info("本用户权限为:" + permission.get("permissionList"));
		//为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * LoginCon	troller.login()方法中执行Subject.login()时 执行此方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		String username = (String) authcToken.getPrincipal();
		String password = new String((char[]) authcToken.getCredentials());
		User user = userService.findBy("username",username);
		if (user == null)
			throw new UnknownAccountException("用户名或密码错误！");
		if (!password.equals(user.getPassword()))
			throw new IncorrectCredentialsException("用户名或密码错误！");
		if (AuthConstants.LOCK_STATUS.equals(user.getStatus()))
			throw new LockedAccountException("账号已被锁定,请联系管理员！");
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
		        user,
				password,
//				ByteSource.Util.bytes("salt"), //salt=username+salt,采用明文访问时，不需要此句
				getName()
		);
		//session中不需要保存密码
        user.setPassword("");
		//将用户信息放入session中
		SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
		return authenticationInfo;
	}
}
