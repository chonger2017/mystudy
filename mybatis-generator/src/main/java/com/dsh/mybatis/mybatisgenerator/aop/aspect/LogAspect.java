package com.dsh.mybatis.mybatisgenerator.aop.aspect;

import com.dsh.mybatis.mybatisgenerator.enity.User;
import com.dsh.mybatis.mybatisgenerator.service.IAuthService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    private static final String EDP = "execution(* com.dsh.mybatis.mybatisgenerator.service.*.*(..))";

    @Before(value = "execution(* com.dsh.mybatis.mybatisgenerator.service.*.*(..))")
    public void before(JoinPoint joinpoint) {
        LOG.info("调用方法之前执行"+joinpoint);
        Object[] args = joinpoint.getArgs();
//        String kind = joinpoint.getKind();
//        System.out.println("kind = " + kind);
        User user = (User) args[0];
        System.out.println(user.getAddress()+"_"+user.getLoginName()+"_"+user.getPassword());
        Arrays.stream(args).forEach( e -> System.out.println(e));
    }

    @AfterReturning(EDP)
    public void afterReturn(JoinPoint joinpoint) {
        LOG.info("调用方法返回值之后执行"+joinpoint);
    }

    @AfterThrowing(EDP)
    public void afterThrow(JoinPoint joinpoint) {
        LOG.info("抛出异常之后"+joinpoint);
    }

    @After(EDP)
    public void after(JoinPoint joinpoint) {
        LOG.info("调用方法之后执行"+joinpoint);
    }
}
