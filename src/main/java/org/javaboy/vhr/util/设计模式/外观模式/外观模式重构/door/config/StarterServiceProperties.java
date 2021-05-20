package org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类注解定义L用于定义后续在application.yml中添加itstack.door的配置信息
 */
@ConfigurationProperties("itstack.door")
public class StarterServiceProperties {

    private String userStr;

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

}
