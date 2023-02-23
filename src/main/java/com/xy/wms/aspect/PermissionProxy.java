package com.xy.wms.aspect;

import com.xy.wms.annotation.RequiredPermission;
import com.xy.wms.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {
    @Resource
    private HttpSession session;
    @Around(value = "@annotation(com.xy.wms.annotation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //的搭配当前登录用户 所拥有的资源
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        //判断用户是否拥有权限
        if (null == permissions || permissions.size()<0){
            //不具有权限 抛出异常
            throw new AuthException();
        }
        //得到对应的目标
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //得到方法上的注解
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        //判断状态码  如果不包含 就抛出异常
        if (!(permissions.contains(requiredPermission.code()))){
            throw new AuthException();
        }
        result = pjp.proceed();
        return result;
    }
}
