package org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design.agent;

import java.lang.annotation.*;

/**
 * 定义可一个模拟mybatis-spring中的自定义注解，用在方法层面
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})//用在方法层面
public @interface Select {

    //传入参数String value -- 即sql
    String value() default "";

}
