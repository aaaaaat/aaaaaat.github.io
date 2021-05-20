package org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.application;

import java.util.concurrent.TimeUnit;

/**
 * 定义redis使用接口
 */
public interface CacheService {

    String get(final String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
