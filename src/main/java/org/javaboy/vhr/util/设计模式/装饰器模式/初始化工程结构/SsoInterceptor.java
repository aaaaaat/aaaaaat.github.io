package org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构;

/**
 * 接口功能SsoInterceptor模拟的单点登录拦截服务
 * 实际的单点登录会基于org.springframework.web.servlet.HandlerInterceptor
 */
public class SsoInterceptor implements HandlerInterceptor{

    public boolean preHandle(String request, String response, Object handler) {
        //实际使用中，需要从HttpServletRequest request对象中获取cookies信息，解析ticket值，并校验。
        //只要获取到了success，就认为是允许登录

        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        return ticket.equals("success");
    }

}
