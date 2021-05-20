package org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.factory;

import org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.workshop.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDKProxyFactory、JDKInvocationHandler两个类分别是代理类的定义和实现，这部分代码主要
 * 通过代理类和反射调用的方式
 * 获取工厂及方法调用
 *
 */
public class JDKProxyFactory {

    //代理方式实现抽象工厂--通过代理类的方式实现一个集群服务处理类
    //就可以非常方便地在Spring、Springboot等框架中通过注入的方式替换原有的CacheServiceImpl（单体服务）

    //参数1：cacheClazz在模拟场景中，不同系统使用的redis服务类名可能会有所不同，通过这样的2方式便于实例化后的注入操作
    //参数2：cacheAdapter这个参数决定实例话哪套集群服务使用redis功能

    //CacheService proxy_EGM = JDKProxyFactory.getProxy(CacheService.class, EGMCacheAdapter.class);
    public static <T> T getProxy(Class cacheClazz, Class<? extends ICacheAdapter> cacheAdapter) throws Exception {

        //实例化哪套集群
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter.newInstance());

        //在代码中动态加载jar、资源文件的时候，首先应该是使用Thread.currentThread().getContextClassLoader()
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //还可以扩充相应的代码：注入的设计、配置的读取、相关缓存和缓存使用开关等
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{cacheClazz}, handler);

    }

}
