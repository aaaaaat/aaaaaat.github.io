package org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.impl;



import org.javaboy.vhr.util.设计模式.责任链模式.场景模拟.AuthService;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.AuthInfo;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.AuthLink;

import java.text.ParseException;
import java.util.Date;

/**
 * 一级负责人
 */
public class Level1AuthLink extends AuthLink {

    private Date beginDate = f.parse("2020-06-11 00:00:00");
    private Date endDate = f.parse("2020-06-20 23:59:59");

    public Level1AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    public AuthInfo doAuth(String uId, String orderId, Date authDate) {

        Date date = AuthService.queryAuthInfo(levelUserId, orderId);

        //先判断审批是否已通过，如果没有通过，则将结果返回给调用方，引导去审批
        //（这里简单模拟审批后有时间信息不为空，作为判断条件）
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待一级审批负责人 ", levelUserName);
        }

        //判断完成后获取下一个审批节点super.next()
        AuthLink next = super.next();

        //如果不存在下一个节点，则直接返回结果
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId
                    , " 状态：三级审批负责人完成", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        //之后根据不同的业务时间判断是否需要二级负责人审批和一级负责人审批
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId
                    , " 状态：三级审批负责人完成", " 时间：", f.format(date), " 审批人：", levelUserName);
        }

        //最后返回下一个审批结果next.doAuth(uId, orderId, authDate),就像递归调用
        return next.doAuth(uId, orderId, authDate);
    }

}
