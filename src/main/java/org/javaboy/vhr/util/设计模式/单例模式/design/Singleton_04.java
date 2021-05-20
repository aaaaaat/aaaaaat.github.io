package org.javaboy.vhr.util.设计模式.单例模式.design;
/**
 * 使用类的内部类；既保证了线程安全，又保证了懒汉模式，同时不会因为加锁而降低性能
 *
 * 主要是因为：jvm虚拟机可以保证多线程并发访问的正确性
 * 也就是一个类的构造方法在多线程情况下可以被正确地加载
 *
 *
 * 线程安全：针对Singleton_04这个类来说，多线程访问时，有且只能被创建一个出来
 */
public class Singleton_04 {

    private static class SingletonHolder {
        private static Singleton_04 instance = new Singleton_04();
    }

    private Singleton_04() {
    }

    //静态内部类的特点：外部类加载时不需要加载静态内部类，不被加载则不占用内存，
    // （延迟加载）当外部类调用getInstance方法时，才加载静态内部类，
    // 静态属性保证了全局唯一，静态变量初始化保证了线程安全，
    // 所以这里的方法没有加synchronized关键字（JVM保证了一个类的 初始化在多线程下被同步加锁）
    public static Singleton_04 getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton_04.getInstance()); // org.itstack.demo.design.Singleton_06@2b193f2d
        System.out.println(Singleton_04.getInstance());
    }
}
