package org.javaboy.vhr.util.设计模式.单例模式.design;
/**
 * 双重锁的方式是方法级锁的优化，减少获取实例的耗时。
 * 同时，这种方式也满足了懒汉模式
 */
public class Singleton_05 {

    private static Singleton_05 instance;

    private Singleton_05() {
    }

    public static Singleton_05 getInstance(){
       if(null != instance) return instance;
       synchronized (Singleton_05.class){
           if (null == instance){
               instance = new Singleton_05();
           }
       }
       return instance;
    }

}
