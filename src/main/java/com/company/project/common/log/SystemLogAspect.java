package com.company.project.common.log;

import com.alibaba.fastjson.JSON;
import com.company.project.auth.model.User;
import com.company.project.common.util.IPUtil;
import com.company.project.system.model.Log;
import com.company.project.system.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LogService logService;

    @Pointcut("@annotation(com.company.project.common.log.SystemLog)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object systemLogAfterReturning(ProceedingJoinPoint point){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Object result = null;
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Log log = new Log();
        log.setIp(IPUtil.getIpAddress(request));
        if(user==null){
            user = (User) SecurityUtils.getSubject().getPrincipal();
        }
        log.setUserId(user.getId());
        log.setUri(request.getRequestURI());
        log.setCreateTime(new Date());

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SystemLog logAnnotation = method.getAnnotation(SystemLog.class);
        if (logAnnotation != null) {
            log.setOpertation(logAnnotation.value());
        }
        log.setParams(JSON.toJSONString(point.getArgs()));
        logService.saveLog(log);
        return result;
    }
}
