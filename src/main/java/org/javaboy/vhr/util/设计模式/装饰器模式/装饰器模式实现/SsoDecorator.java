package org.javaboy.vhr.util.设计模式.装饰器模式.装饰器模式实现;

import org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构.HandlerInterceptor;

/**
 * 装饰类：定义抽象类implements HandlerInterceptor（想要扩展的SsoInterceptor（也是 implements HandlerInterceptor））
 * 并继承接口HandlerInterceptor中的方法，
 * 保证一致性（使HandlerInterceptor的另一个实现SsoInterceptor可以作为参数传入）
 *
 * 重点：表示一个抽象类主要完成了对接口HandlerInterceptor的继承
 * 当装饰角色继承接口后，会提供构造函数SsoDecorator(HandlerInterceptor handlerInterceptor)
 * 入参是继承的接口实现类，可以很方便地扩展出不同的功能组件
 */
public abstract class SsoDecorator implements HandlerInterceptor {

    private HandlerInterceptor handlerInterceptor;

    private SsoDecorator(){}

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    public boolean preHandle(String request, String response, Object handler) {
        return handlerInterceptor.preHandle(request, response, handler);
    }

        //1、继承了处理接口HandlerInterceptor
        // 2、提供了构造函数SsoDecorator(HandlerInterceptor handlerInterceptor)
        // 3、覆盖了preHandle

        //以上三点是装饰器模式的核心处理部分
        //可以替换对子类继承的方式，实现逻辑功能的扩展
}
