package com.company.project.common.shiro;

import com.company.project.common.core.ResultCode;
import com.company.project.common.core.ResultGenerator;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author: caoxile
 * @description: 对没有登录的请求进行拦截, 全部返回json信息. 覆盖掉shiro原本的跳转login.jsp的拦截方式
 * @date: 2017/10/24 10:11
 */
public class AuthorizationFilter extends FormAuthenticationFilter {

	// 对于没有认证(或session过期)的请求，需要允许重写过滤器：1 返回未认证JSON 2 允许跨域访问
	// 关于跨域的设置：1认证通过的交给Spring的addCorsMappings处理 2 认证没有通过的这里设置
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
		PrintWriter out = null;
		try {
			setHeader((HttpServletRequest) request,(HttpServletResponse) response);
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

	private void setHeader(HttpServletRequest request, HttpServletResponse response){
		//跨域的header设置
		response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", request.getMethod());
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		response.setHeader("Content-Type","application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());
	}
}
