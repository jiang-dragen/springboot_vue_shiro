package com.jiang.back.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


/*
@project name back
@author jiang
@time   2021-09-17-9:21
        日志切面
*/
@Aspect
@Component
public class ServiceLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.jiang.back.aspect.ServiceLogAspect.class);

    //切面
    @Pointcut("execution(* com.jiang.back.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null){
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteHost();
        String now = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName();
        LOGGER.info(String.format("用户[%s],在[%s],访问了[%s].",ip,now,target));
    }
}
