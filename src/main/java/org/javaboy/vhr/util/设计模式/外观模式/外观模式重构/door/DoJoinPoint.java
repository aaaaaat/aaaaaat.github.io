package org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door;


import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.annotation.DoDoor;
import org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.config.StarterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 注解的具体实现
 * 在实际开发中，我们将中间件打jar包，将这种jar包上传到maven仓库，供调用方使用
 */
@Aspect
@Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);

    //获取useStr
    @Autowired
    private StarterService starterService;

    //定义切面，这里采用的是注解路径
    //也就是所有加入这个注解的方法都会被切面管理
    @Pointcut("@annotation(util.设计模式.外观模式.外观模式重构.door.annotation.DoDoor)")
    public void aopPoint() {
    }

    //切面核心逻辑，这部分主要是判断当前访问的用户id是否为白名单用户。
    //如果是，则放行 jp.proceed();
    //否则返回自定义的拦截提示信息
    @Around("aopPoint()") //所有加入这个注解的方法都会被切面aopPoint()管理
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {

        //@DoDoor(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")

        //获取切面内容
        Method method = getMethod(jp);
        //切面内容中包括注解
        DoDoor door = method.getAnnotation(DoDoor.class);
        //获取字段值（door的两个入参）
        // key获取某个字段，例如用户id；
        // returnJson确定白名单拦截后返回的具体内容
        String keyValue = getFiledValue(door.key(), jp.getArgs());
        logger.info("itstack door handler method：{} value：{}", method.getName(), keyValue);


        if (null == keyValue || "".equals(keyValue)) return jp.proceed();

        //配置内容（白名单中人员：aa,bb,cc）
        String[] split = starterService.split(",");
        //白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return jp.proceed();
            }
        }

        //拦截
        return returnObject(door, method);
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return getClass(jp).getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }

    //返回对象
    //返回拦截后的转换对象，当非白名单用户访问时，会返回一些提示信息
    private Object returnObject(DoDoor doGate, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();

        //DoDoor doGate
        //两个入参：useStr、returnJson
        // * key获取某个字段，例如用户id；
        // * returnJson确定白名单拦截后返回的具体内容
        String returnJson = doGate.returnJson();

        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }

        return JSON.parseObject(returnJson, returnType);
    }

    //获取属性值
    //获取指定key，也就是获取入参中的某个属性，这里主要是获取用户id，通过id拦截校验
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }

}
