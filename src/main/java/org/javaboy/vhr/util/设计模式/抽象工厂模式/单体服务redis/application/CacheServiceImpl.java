package org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.application;

import org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.redis.RedisUtils;

import java.util.concurrent.TimeUnit;

/**
 * 实现redis使用接口：使用单体redis服务redisUtil
 *
 * 后续会通过两种方式将这部分代码扩展为使用redis集群服务
 *
 */
public class CacheServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    public String get(String key) {
        return redisUtils.get(key);
    }

    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        redisUtils.del(key);
    }

}
