package org.javaboy.vhr.util.设计模式.装饰器模式.装饰器模式实现;

import org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构.HandlerInterceptor;
import org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构.SsoInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 继承装饰类SsoDecorator
 * LoginSsoDecorator是装饰类继承装饰类SsoDecorator的具体实现
 */
public class LoginSsoDecorator extends SsoDecorator {

    private Logger logger = LoggerFactory.getLogger(LoginSsoDecorator.class);

    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        //关键
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {

        //super.preHandle(request, response, handler)调用了传入handler（SsoInterceptor（实现了HandlerInterceptor））中的preHandle

//        // 模拟获取cookie
//        String ticket = request.substring(1, 8);
//        // 模拟校验
//        return ticket.equals("success");

        boolean success = super.preHandle(request, response, handler);
        if (!success) return false;


        // extends SsoDecorator 继承了装饰类SsoDecorator
        // 现在可以扩展方法preHandle的功能
        String userId = request.substring(8);
        String method = authMap.get(userId);
        logger.info("模拟单点登录方法访问拦截校验：{} {}", userId, method);
        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }

    public static void main(String[] args) {

        //将原油单点登录类new SsoInterceptor()传递给装饰器
        //让具体实现的装饰器LoginSsoDecorator可以执行扩充的功能
        LoginSsoDecorator ssoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }
}
