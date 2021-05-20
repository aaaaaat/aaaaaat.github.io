package org.javaboy.vhr.util.设计模式.装饰器模式.违背设计模式实现;

import org.javaboy.vhr.util.设计模式.装饰器模式.初始化工程结构.SsoInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 继承类后重写方法，并将逻辑覆盖进去。
 *  extends SsoInterceptor
 * 对于一些简单的且不需要持续维护和扩展的场景，这种方式的实现并不会有什么问题，也不会导致子类过多
 */
public class LoginSsoDecorator extends SsoInterceptor {

    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        //设定好两个可以访问的用户ID:huahua、doudou
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {

        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        boolean success = ticket.equals("success");

        if (!success) return false;


        String userId = request.substring(8);
        String method = authMap.get(userId);

        // 将个人可访问方法功能添加到方法中
        return "queryUserInfo".equals(method);
    }

    public static void main(String[] args) {

        //模拟登录检验，判断用户是否可以登录以及是否可以访问方法
    }

}
