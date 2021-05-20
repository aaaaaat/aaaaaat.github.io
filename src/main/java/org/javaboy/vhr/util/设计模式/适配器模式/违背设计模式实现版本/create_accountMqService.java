package org.javaboy.vhr.util.设计模式.适配器模式.违背设计模式实现版本;


import com.alibaba.fastjson.JSON;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.mq.create_account;

public class create_accountMqService {

    public void onMessage(String message) {

        //JSON.parseObject（）
        create_account mq = JSON.parseObject(message, create_account.class);

        mq.getNumber();
        mq.getAccountDate();

        // ... 处理自己的业务

    }

}
