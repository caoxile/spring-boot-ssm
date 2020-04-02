package com.company.project.common.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: caoxile
 * @description: shiro配置类
 * @date: 2017/10/24 10:10
 */
@Configuration
public class ShiroConfiguration {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;
	/**
	 * Shiro的Web过滤器Factory 命名:shiroFilter
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("authc", new AuthorizationFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		/*定义shiro过滤链  Map结构
		 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
		 * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
		 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
         /* 过滤链定义，从上向下顺序执行，一般将 / ** 放在最为下边:这是一个坑呢，一不小心代码就不好使了;
          authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
		filterChainDefinitionMap.put("/", "anon");
//		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/auth/login", "anon");
//		filterChainDefinitionMap.put("/auth/logout", "anon");
		filterChainDefinitionMap.put("/druid/*", "anon");
		filterChainDefinitionMap.put("/druid/*/*", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 不指定名字的话，自动创建一个方法名第一个字母小写的bean
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		securityManager.setCacheManager(cacheManager());
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * Shiro Realm 继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义的
	 */
	@Bean
	public UserRealm userRealm() {
		return new UserRealm();
	}

//	/**
//	 * 凭证匹配器
//	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
//	 * 所以我们需要修改下doGetAuthenticationInfo中的代码;
//	 * ）
//	 * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
//	 */
//	@Bean(name = "credentialsMatcher")
//	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		//散列算法:这里使用MD5算法;
//		hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
//		//散列的次数，比如散列两次，相当于 md5(md5(""));
//		hashedCredentialsMatcher.setHashIterations(1);
//		//storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
//		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
//		return hashedCredentialsMatcher;
//	}

//	/**
//	 * Shiro生命周期处理器
//	 */
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//
//	/**
//	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
//	 */
//	@Bean
//	@DependsOn({"lifecycleBeanPostProcessor"})
//	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		advisorAutoProxyCreator.setProxyTargetClass(true);
//		return advisorAutoProxyCreator;
//	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * shiro 中配置 redis 缓存
	 *
	 * @return RedisManager
	 */
	private RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		// 缓存时间，单位为秒
		//redisManager.setExpire(febsProperties.getShiro().getExpireIn()); // removed from shiro-redis v3.1.0 api
		redisManager.setHost(host+":"+port);
		if (StringUtils.isNotBlank(password))
			redisManager.setPassword(password);
		redisManager.setTimeout(timeout);
		return redisManager;
	}

	private RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;

	}

	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * session 管理对象
	 *
	 * @return DefaultWebSessionManager
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// 设置session超时时间，单位为毫秒
		sessionManager.setGlobalSessionTimeout(30*60*1000);
		sessionManager.setSessionDAO(redisSessionDAO());
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		return sessionManager;
	}
}
