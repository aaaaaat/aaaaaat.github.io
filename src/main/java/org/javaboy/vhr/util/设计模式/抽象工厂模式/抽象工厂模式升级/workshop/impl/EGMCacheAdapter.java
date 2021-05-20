package org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.workshop.impl;


import org.javaboy.vhr.util.设计模式.抽象工厂模式.单体服务redis.redis.cluster.EGM;
import org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.workshop.ICacheAdapter;

import java.util.concurrent.TimeUnit;

public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
}
