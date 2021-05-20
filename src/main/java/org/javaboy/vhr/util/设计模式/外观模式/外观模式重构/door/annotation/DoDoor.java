package org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面注解定义了外观模式切面注解，后续将此注解添加到需要扩展白名单的方法上
 * 这里提供了两个入参：
 * key获取某个字段，例如用户id；
 * returnJson确定白名单拦截后返回的具体内容
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoDoor {

    String key() default "";

    String returnJson() default "";

}
