package org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构;

public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}
