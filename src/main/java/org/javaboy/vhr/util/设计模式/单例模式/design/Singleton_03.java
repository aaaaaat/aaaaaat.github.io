package org.javaboy.vhr.util.设计模式.单例模式.design;

/**
 * 饿汉模式（线程安全）
 * 在程序启动时直接运行加载，后续有外部需要使用时获取即可
 * 这种方式并不是懒加载
 * 例如：游戏地图还没打开，但是程序已经将这些地图全部实例化，造成手机卡顿
 */
public class Singleton_03 {

    private static Singleton_03 instance = new Singleton_03();

    private Singleton_03() {
    }

    public static Singleton_03 getInstance() {
        return instance;
    }

}
