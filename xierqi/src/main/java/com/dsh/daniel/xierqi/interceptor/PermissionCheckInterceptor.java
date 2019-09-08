package com.dsh.daniel.xierqi.interceptor;

import com.dsh.daniel.xierqi.annotation.PermissionCheck;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PermissionCheck permission = findPermissionCheck(handlerMethod);

        if (permission == null) {
            return true;
        }

        //获取注解中的值
        String resourceKey = permission.resouceKey();

        if ("testKey".equals(resourceKey)) {
            return true;
        }

        return false;
    }

    private PermissionCheck findPermissionCheck(HandlerMethod handlerMethod) {
        PermissionCheck permission = handlerMethod.getMethodAnnotation(PermissionCheck.class);
        if (permission == null) {
            //在类上寻求注解
            permission = handlerMethod.getBeanType().getAnnotation(PermissionCheck.class);
        }

        return permission;
    }
}
