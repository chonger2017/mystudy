package com.dsh.daniel.xierqi.annotation;

import com.dsh.daniel.xierqi.util.ParamConstraintValidated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//只允许用在类的字段上
@Target({ElementType.FIELD})
//注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ParamConstraintValidated.class)
public @interface Check {

    /**
     * 合法的参数值
     *
     * @return
     */
    String[] paramValues();

    String message() default "参数不为指定值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
