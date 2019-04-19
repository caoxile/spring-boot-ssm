package com.company.project.common.shiro;

import com.company.project.common.core.ResultCode;
import com.company.project.common.core.ResultGenerator;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author: caoxile
 * @description: 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @date: 2017/10/24 10:11
 */
public class AuthorizationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
		PrintWriter out = null;
		try {
			response.getWriter().println(ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED,"未认证"));
		} catch (Exception e) {
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
		return false;
	}

}
