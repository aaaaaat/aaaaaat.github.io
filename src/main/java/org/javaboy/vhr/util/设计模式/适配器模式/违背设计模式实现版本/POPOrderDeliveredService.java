package org.javaboy.vhr.util.设计模式.适配器模式.违背设计模式实现版本;

import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.mq.POPOrderDelivered;

public class POPOrderDeliveredService {

    public void onMessage(String message) {

        POPOrderDelivered mq = JSON.parseObject(message, POPOrderDelivered.class);

        mq.getuId();
        mq.getOrderId();
        mq.getOrderTime();

        // ... 处理自己的业务
    }

}
