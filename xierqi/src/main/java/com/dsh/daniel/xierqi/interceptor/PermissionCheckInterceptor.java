package com.dsh.daniel.xierqi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dsh.daniel.xierqi.annotation.PermissionCheck;
import com.dsh.daniel.xierqi.domain.Response;
import com.dsh.daniel.xierqi.domain.VO.ResponseVO;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        PermissionCheck permission = findPermissionCheck(handlerMethod);

        if (permission == null) {
            return true;
        }

        //获取注解中的值
        String resourceKey = permission.resourceKey();

        if ("test".equals(resourceKey)) {
            return true;
        }
        ResponseVO<Void> responseVO = new ResponseVO<>(401,null,"you aren't test user");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject res = new JSONObject();
        res.put("status","401");
        res.put("message","need login");
        res.put("data", "");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(res.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("======================postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("======================afterCompletion");
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
