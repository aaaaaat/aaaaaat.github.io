package org.javaboy.vhr.util.设计模式.抽象工厂模式.抽象工厂模式升级.util;

import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 * 工具包：主要用于支撑反射方法调用中参数的处理
 */
public class ClassLoaderUtils {

    public static Class<?>[] getClazzByArgs(Object[] args) {

        Class<?>[] parameterTypes = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ArrayList) {
                parameterTypes[i] = List.class;
                continue;
            }
            if (args[i] instanceof LinkedList) {
                parameterTypes[i] = List.class;
                continue;
            }
            if (args[i] instanceof HashMap) {
                parameterTypes[i] = Map.class;
                continue;
            }
            if (args[i] instanceof Long){
                parameterTypes[i] = long.class;
                continue;
            }
            if (args[i] instanceof Double){
                parameterTypes[i] = double.class;
                continue;
            }
            if (args[i] instanceof TimeUnit){
                parameterTypes[i] = TimeUnit.class;
                continue;
            }
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }

}

