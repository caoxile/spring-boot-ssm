package com.company.project.common.config;

import com.company.project.common.core.Result;
import com.company.project.common.core.ResultCode;
import com.company.project.common.core.ResultGenerator;
import com.company.project.common.core.ServiceException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: caoxile
 * @description: 统一异常拦截
 * @date: 2019/04/01
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/**
	 * 默认系统异常
	 */
	@ExceptionHandler(value = Exception.class)
	public Result defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler ,Exception e) {
		String message;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
					request.getRequestURI(),
					handlerMethod.getBean().getClass().getName(),
					handlerMethod.getMethod().getName(),
					e.getMessage());
		} else {
			message = e.getMessage();
		}
		logger.error(message, e);
		return ResultGenerator.genFailResult(ResultCode.INTERNAL_SERVER_ERROR,"接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
    }

	/**
	 * 自定义异常
	 */
	@ExceptionHandler({IllegalArgumentException.class,ServiceException.class})
	public Result serviceExceptionHandler(Exception exception) {
		return ResultGenerator.genFailResult(exception.getMessage());
	}

	/**
	 * HTTP请求参数异常
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result requestParameterExceptionHandler() {
		return ResultGenerator.genFailResult("请求参数错误");
	}

    /**
     * HTTP请求方法错误或接口不存在
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,NoHandlerFoundException.class})
    public Result httpRequestMethodHandler() {
        return ResultGenerator.genFailResult(ResultCode.NOT_FOUND,"接口不存在");
    }


	/**
	 * 权限不足
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public Result unauthorizedExceptionHandler() {
		return ResultGenerator.genFailResult("没有权限");
	}

	/**
	 * 未认证(未登录)
	 */
	@ExceptionHandler(UnauthenticatedException.class)
	public Result unauthenticatedException() {
		return ResultGenerator.genFailResult(ResultCode.UNAUTHORIZED,"未认证");
	}
}
