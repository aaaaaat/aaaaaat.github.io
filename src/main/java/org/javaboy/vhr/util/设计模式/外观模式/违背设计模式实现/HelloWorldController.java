package org.javaboy.vhr.util.设计模式.外观模式.违背设计模式实现;


import org.javaboy.vhr.util.设计模式.外观模式.domain.UserInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单的逻辑代码占据了一大块，但他不是业务功能流程中的逻辑，只是因为上线过程需要在开量前测试验证
 */
public class HelloWorldController {

    public UserInfo queryUserInfo(@RequestParam String userId) {

        // 做白名单拦截
        List<String> userList = new ArrayList<String>();
        userList.add("1001");
        userList.add("aaaa");
        userList.add("ccc");
        if (!userList.contains(userId)) {
            return new UserInfo("1111", "非白名单可访问用户拦截！");
        }

        return new UserInfo("虫虫:" + userId, 19, "天津市南开区旮旯胡同100号");
    }

}
