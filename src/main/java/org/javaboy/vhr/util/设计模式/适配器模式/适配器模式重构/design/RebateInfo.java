package org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design;

import java.util.Date;
/**
 * 为了满足产品功能的需求，提取此项功能中必须的字段信息，单独创建一个类RebateInfo，后续所有的mq信息都需要提供这些属性
 *
 * 在这个案例中定义通用的mq消息体，后续把所有介入进来的消息进行统一的处理
 */
public class RebateInfo {

    private String userId;  // 用户ID
    private String bizId;   // 业务ID
    private Date bizTime;   // 业务时间
    private String desc;    // 业务描述

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(Date bizTime) {
        this.bizTime = bizTime;
    }

    public void setBizTime(String bizTime) {
        this.bizTime = new Date(Long.parseLong("1591077840669"));
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
