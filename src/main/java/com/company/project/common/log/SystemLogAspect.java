package com.company.project.common.log;

import com.alibaba.fastjson.JSON;
import com.company.project.auth.model.User;
import com.company.project.common.util.IPUtil;
import com.company.project.system.model.Log;
import com.company.project.system.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Package com.company.project.common.log
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2019-04-08
 */
@Component
@Aspect
public class SystemLogAspect {

    @Resource
    private LogService logService;

    @Pointcut("@annotation(com.company.project.common.log.SystemLog)")
    public void pointcut() {}

    @After("pointcut()")
    public void systemLogAfterReturning(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Log log = new Log();
        log.setIp(IPUtil.getIpAddress(request));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        log.setUserId(user.getId());
        log.setUri(request.getRequestURI());
        log.setCreateTime(new Date());

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog logAnnotation = method.getAnnotation(SystemLog.class);
        if (logAnnotation != null) {
            log.setOpertation(logAnnotation.value());
        }
//        log.setParams(request.getQueryString());
        log.setParams(JSON.toJSONString(joinPoint.getArgs()));
        logService.save(log);
    }
}
