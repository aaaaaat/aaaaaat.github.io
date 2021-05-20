package org.javaboy.vhr.util.设计模式.抽象工厂模式.错误升级;

import java.util.concurrent.TimeUnit;

public interface CacheService {

    String get(final String key, int redisType);

    void set(String key, String value, int redisType);

    void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType);

    void del(String key, int redisType);

}
