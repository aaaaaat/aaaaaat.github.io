package org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design;

import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.mq.OrderMq;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.mq.create_account;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MQAdapter {

    //把不同类型的mq中的各种属性映射成需要的属性并返回
    public static RebateInfo filter(String strJson, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        return filter(JSON.parseObject(strJson, Map.class), link);
    }
    /**
     * 同样的字段值在实现适配前后，分别有统一的字段属性
     * 除了反射的使用。还可以加入代理类，把映射的配置交给代理类，不需要手动创建类的每一个mq
     */
    public static RebateInfo filter(Map obj, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //比如一个属性中有用户ID uId 映射到 RebateInfo 中userId ，然后做统一处理
        //这个处理过程需要把映射管理传递给Map<String,String>link--也就是准确的描述了当前mq中的某个属性名称，映射为指定的某个属性名称
        //1、接收的mq消息基本是json格式，可以转化为map结构
        //2、使用反射调用的方式对类型赋值
        RebateInfo rebateInfo = new RebateInfo();

        for (String key : link.keySet()) {
            Object val = obj.get(link.get(key));
            //"set" + key.substring(0, 1).toUpperCase() + key.substring(1) 获取set方法
            // link{{"userId"，"number"},{... , ...},{... , ...}} userId映射为number
            //在世纪业务开发中可以吧这种映射配置关系交给配置文件或数据库后台，以减少编码
            RebateInfo.class.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class).invoke(rebateInfo, val.toString());
        }

        return rebateInfo;
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParseException {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = s.parse("2020-06-01 23:20:16");


        create_account create_account = new create_account();
        create_account.setNumber("100001");
        create_account.setAddress("河北省.廊坊市.广阳区.大学里职业技术学院");
        create_account.setAccountDate(parse);
        create_account.setDesc("在校开户");

        HashMap<String, String> link01 = new HashMap<String, String>();
        link01.put("userId", "number");
        link01.put("bizId", "number");
        link01.put("bizTime", "accountDate");
        link01.put("desc", "desc");
        RebateInfo rebateInfo01 = MQAdapter.filter(create_account.toString(), link01);
        System.out.println("mq.create_account(适配前)" + create_account.toString());
        System.out.println("mq.create_account(适配后)" + JSON.toJSONString(rebateInfo01));

        System.out.println("");

        OrderMq orderMq = new OrderMq();
        orderMq.setUid("100001");
        orderMq.setSku("10928092093111123");
        orderMq.setOrderId("100000890193847111");
        orderMq.setCreateOrderTime(parse);

        HashMap<String, String> link02 = new HashMap<String, String>();
        link02.put("userId", "uid");
        link02.put("bizId", "orderId");
        link02.put("bizTime", "createOrderTime");
        RebateInfo rebateInfo02 = MQAdapter.filter(orderMq.toString(), link02);
        System.out.println("mq.orderMq(适配前)" + orderMq.toString());
        System.out.println("mq.orderMq(适配后)" + JSON.toJSONString(rebateInfo02));
    }
}
