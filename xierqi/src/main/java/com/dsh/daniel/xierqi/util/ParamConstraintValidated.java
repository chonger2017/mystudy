package com.dsh.daniel.xierqi.util;

import com.alibaba.fastjson.JSONObject;
import com.dsh.daniel.xierqi.annotation.Check;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class ParamConstraintValidated implements ConstraintValidator<Check, Object> {
    /**
     * 合法的参数值，从注解中获取
     */
    private List<String> paramValues;

    @Override
    public void initialize(Check constraintAnnotation) {
        //初始化是获取注解上的值
        paramValues = Arrays.asList(constraintAnnotation.paramValues());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        if (paramValues.contains(value)) {
            return true;
        }
        //不再指定的参数列表中
        return false;
    }
}
