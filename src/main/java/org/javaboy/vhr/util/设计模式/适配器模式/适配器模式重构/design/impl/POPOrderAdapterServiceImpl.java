package org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design.impl;


import org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design.OrderAdapterService;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.service.POPOrderService;

/**
 * 第三方订单，判断首单逻辑
 */
public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}
