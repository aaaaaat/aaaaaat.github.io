package org.javaboy.vhr.util.设计模式.代理模式.main.java.cn.bugstack.design.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理类定义
 * 模拟mybatis源码中的MapperFactoryBean类
 */

//FactoryBean：用来处理bean工厂的类
//implements FactoryBean<T>  通过继承factoryBean，提供对象Bean，也就是方法 T getObject（）
//需要实现：1、构造函数透传 2、getObject（） 对@Select("select userName from user where id = #{uId}")的处理逻辑
//         3、提供对象类型反馈 4、返回类是否单例
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Logger logger = LoggerFactory.getLogger(MapperFactoryBean.class);

    private Class<T> mapperInterface;

    //构造函数透传（需要被代理的类Class<T> mapperInterface）对应Interface，例如IUserDao
    //在mybatis中也使用这种方式透传
    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    //在方法getObject（）中提供类的代理，并模拟对sql语句的处理，这里包含当用户调用Dao层方法时的处理逻辑
    //@Select("select userName from user where id = #{uId}") select.value（）就是里面的sql
    @Override
    public T getObject() throws Exception {

        InvocationHandler handler = (proxy, method, args) -> {
            Select select = method.getAnnotation(Select.class);
            logger.info("SQL：{}", select.value().replace("#{uId}", args[0].toString()));
            return args[0] + "";
        };
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, handler);
    }

    //提供对象类型反馈
    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    //返回类是单例的
    @Override
    public boolean isSingleton() {
        return true;
    }

}
