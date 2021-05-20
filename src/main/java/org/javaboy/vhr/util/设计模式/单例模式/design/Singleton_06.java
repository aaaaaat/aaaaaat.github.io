package org.javaboy.vhr.util.设计模式.单例模式.design;

import java.util.concurrent.atomic.AtomicReference;
/**
 * CAS "AtomicReference<V>"(线程安全)
 * AtomicReference<V>可以封装饮用一个V实例
 * 使用cas的好处是不需要使用传统的加锁方式，而是依赖cas的忙等算法、底层硬件的实现保证线程安全
 */

public class Singleton_06 {

    private static final AtomicReference<Singleton_06> INSTANCE = new AtomicReference<Singleton_06>();

    private static Singleton_06 instance;

    private Singleton_06() {
    }

    public static final Singleton_06 getInstance() {
        for (; ; ) {
            Singleton_06 instance = INSTANCE.get();
            if (null != instance) return instance;
            INSTANCE.compareAndSet(null, new Singleton_06());
            return INSTANCE.get();
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton_06.getInstance()); // org.itstack.demo.design.Singleton_06@2b193f2d
        System.out.println(Singleton_06.getInstance()); // org.itstack.demo.design.Singleton_06@2b193f2d
    }


}
