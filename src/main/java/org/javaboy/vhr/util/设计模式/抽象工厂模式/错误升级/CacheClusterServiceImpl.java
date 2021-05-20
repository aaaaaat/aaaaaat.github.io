package org.javaboy.vhr.util.设计模式.抽象工厂模式.错误升级;



import org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.redis.RedisUtils;
import org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.redis.cluster.EGM;
import org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.redis.cluster.IIR;

import java.util.concurrent.TimeUnit;

/**
 * 升级后，使用多套Redis集群服务，同时兼容以前单体Redis服务
 */
public class CacheClusterServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    private EGM egm = new EGM();

    private IIR iir = new IIR();

    public String get(String key, int redisType) {

        if (1 == redisType) {
            return egm.gain(key);
        }

        if (2 == redisType) {
            return iir.get(key);
        }

        return redisUtils.get(key);
    }

    public void set(String key, String value, int redisType) {

        if (1 == redisType) {
            egm.set(key, value);
            return;
        }

        if (2 == redisType) {
            iir.set(key, value);
            return;
        }

        redisUtils.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit, int redisType) {

        if (1 == redisType) {
            egm.setEx(key, value, timeout, timeUnit);
            return;
        }

        if (2 == redisType) {
            iir.setExpire(key, value, timeout, timeUnit);
            return;
        }

        redisUtils.set(key, value, timeout, timeUnit);
    }

    public void del(String key, int redisType) {

        if (1 == redisType) {
            egm.delete(key);
            return;
        }

        if (2 == redisType) {
            iir.del(key);
            return;
        }

        redisUtils.del(key);
    }

}
