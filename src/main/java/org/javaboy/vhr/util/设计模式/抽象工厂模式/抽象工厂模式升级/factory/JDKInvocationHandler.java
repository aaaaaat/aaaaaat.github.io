package org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.factory;

import org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.util.ClassLoaderUtils;
import org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.workshop.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 代理类的实现：implements InvocationHandler
 * InvocationHandler接口是proxy代理实例的调用处理程序实现的一个接口
 * 每一个proxy代理实例都有一个关联的调用处理程序
 */
public class JDKInvocationHandler implements InvocationHandler {

    private ICacheAdapter cacheAdapter;

    //构造方法
    public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    // InvocationHandler接口是proxy代理实例的调用处理程序实现的一个接口，每一个proxy代理实例都有一个关联的调用处理程序；

    // 在代理实例调用方法时--在调用方法的时候！！！！！
    // 方法调用被编码分派到调用处理程序的invoke方法。
    // 参数proxy：哪个抽象代理工厂调用的
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //在反射调用过程中，通过入参（args）获取需要调用的方法名和参数，可以调用对应redis集群中的方法

        //args[0]"user_name_01" [1]"小德哥" set方法，调用一次classLoaderUtils转化两个参数
        //调用两个getMethod方法分别传入set两个参数
        return ICacheAdapter.class.getMethod(method.getName(), //ICacheAdapter接口接口对应方法
                    ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);// cacheAdapter对应实例

        //执行过程：先外层invoke 转化两个传入参数，传入ICacheAdapter对应set方法
        //        再内层invoke 转到invoke对应service比如EGM.set方法，并传入两个参数


        //ClassLoaderUtils.getClazzByArgs(args))获取参数数组对应class（）.例如：list.class map.class...
        //作为cacheAdaptor的输入
    }

}
