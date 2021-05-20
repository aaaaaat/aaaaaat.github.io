package org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.config;

import org.springframework.util.StringUtils;

/**
 * 配置服务类：为了获取springboot中配置文件的信息内容
 */
public class StarterService {

    private String userStr;

    public StarterService(String userStr) {
        this.userStr = userStr;
    }

    public String[] split(String separatorChar) {
        return StringUtils.split(this.userStr, separatorChar);
    }

}
