package org.javaboy.vhr.util.设计模式.单例模式.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton_00 {

    //第一次运行时直接初始化map类，同时也不需要直到延迟加载在使用。
    //在不需要维持任何状态的情况下，仅仅用于全局访问，使用静态类更加方便
    //在需要被继承及维持一些特定状态的情况下，适合使用单例模式
    public static Map<String,String> cache = new ConcurrentHashMap<String, String>();

}
