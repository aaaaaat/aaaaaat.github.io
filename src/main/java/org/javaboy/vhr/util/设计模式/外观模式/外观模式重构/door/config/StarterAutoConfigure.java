package org.javaboy.vhr.util.设计模式.外观模式.外观模式重构.door.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 获取自定配置类信息
 * # 自定义中间件配置
 * #itstack:
 *   #door:
 *    #enabled: true
 *    #userStr: 1001,aaaa,ccc #白名单用户ID，多个逗号隔开
 */
@Configuration
@ConditionalOnClass(StarterService.class)
@EnableConfigurationProperties(StarterServiceProperties.class)
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "itstack.door", value = "enabled", havingValue = "true") //白名单开关，作为条件
    StarterService starterService() {
        return new StarterService(properties.getUserStr());
    }

}