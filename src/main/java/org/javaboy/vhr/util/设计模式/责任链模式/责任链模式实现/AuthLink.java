package org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现;

import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.util.设计模式.装饰器模式.装饰器模式实现.LoginSsoDecorator;
import org.javaboy.vhr.util.设计模式.责任链模式.场景模拟.AuthService;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.impl.Level1AuthLink;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.impl.Level2AuthLink;
import org.javaboy.vhr.util.设计模式.责任链模式.责任链模式实现.impl.Level3AuthLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 审核规定；
 * 1. 601-610 三级审批 + 二级审批
 * 2. 611-620 三级审批 + 二级审批 + 一级审批
 * 3. 其他时间 三级审批
 *
 * 这部分是责任链链接起来的核心部分
 */
public abstract class AuthLink {

    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);

    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式化

    //---责任链中公用信息，标记每一个审批节点的人员信息
    protected String levelUserId;                           // 级别人员ID
    protected String levelUserName;                         // 级别人员姓名

    private AuthLink next;                                  // 责任链，可以通过next方式获取下一个链路需要处理的节点

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    //可以通过next方式获取下一个链路需要处理的节点
    public AuthLink next() {
        return next;
    }


    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }

    //抽象类中定义了一个抽象方法abstract AuthInfo doAuth
    //是每一个实现者必须实现的类，不同的审批级别人员处理不同的业务
    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);


    public static void main(String[] args) throws ParseException {

        Logger logger = LoggerFactory.getLogger(AuthLink.class);

        //责任链创建：通过把不同的责任节点进行组装，构成一条完整业务的责任链
        //传入参数：级别人员levelUserId，级别人员名称levelUserName
        //先三级 再二级 后一级
        AuthLink authLink = new Level3AuthLink("1000013", "王工")
                                .appendNext(new Level2AuthLink("1000012", "张经理")
                                    .appendNext(new Level1AuthLink("1000011", "段总")));

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = f.parse("2020-06-18 23:49:46");

        //doAuth(String uId, String orderId, Date authDate);
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥",
                                                  "1000998004813441", currentDate)));

        //--------加下来不断地执行查看审批链路authLink.doAuth("小傅哥", "1000998004813441", currentDate）
        //--------通过返回结果对数据进行三级负责人、二级负责人和一级负责人审批，直至最后审批完成
        //--------"0000"表示还在走审批流程
        //--------"0001"表示审批结束

        // 模拟三级负责人审批
        ///审批
        //    public static void auth(String uId, String orderId) {
        //        authMap.put(uId.concat(orderId), new Date());
        //    }
        // authMap可以查到审批结果
        AuthService.auth("1000013", "1000998004813441");
        logger.info("测试结果：{}", "模拟三级负责人审批，王工");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", currentDate)));

        // 模拟二级负责人审批
        AuthService.auth("1000012", "1000998004813441");
        logger.info("测试结果：{}", "模拟二级负责人审批，张经理");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", currentDate)));

        // 模拟一级负责人审批
        AuthService.auth("1000011", "1000998004813441");
        logger.info("测试结果：{}", "模拟一级负责人审批，段总");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("小傅哥", "1000998004813441", currentDate)));


    }
}
