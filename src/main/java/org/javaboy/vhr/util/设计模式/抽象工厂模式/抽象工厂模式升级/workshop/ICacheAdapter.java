package org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.workshop;

import java.util.concurrent.TimeUnit;

/**
 * 车间适配器--方法名和参数等略有不同，通过适配接口做到统一的服务输出
 * 包装两个集群服务
 * 例如：IIR的集群的iir.
 */
public interface ICacheAdapter {

    //接口方法没有具体实现
    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
