package org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.impl;


import org.javaboy.vhr.util.设计模式.责任链模式.场景模拟.AuthService;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.AuthInfo;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.AuthLink;

import java.util.Date;

/**
 * 三级负责人
 */
public class Level3AuthLink extends AuthLink {

    public Level3AuthLink(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    public AuthInfo doAuth(String uId, String orderId, Date authDate) {

        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批负责人 ", levelUserName);
        }

        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：一级审批完成负责人", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        return next.doAuth(uId, orderId, authDate);
    }

}
