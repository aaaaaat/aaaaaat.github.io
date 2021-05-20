package org.javaboy.vhr.util.设计模式.适配器模式.违背设计模式实现版本;


import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.mq.OrderMq;

public class OrderMqService {

    public void onMessage(String message) {

        OrderMq mq = JSON.parseObject(message, OrderMq.class);

        mq.getUid();
        mq.getOrderId();
        mq.getCreateOrderTime();

        // ... 处理自己的业务
    }

}
