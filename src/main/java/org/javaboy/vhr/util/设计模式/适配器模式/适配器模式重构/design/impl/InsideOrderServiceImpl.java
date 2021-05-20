package org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design.impl;


import org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design.OrderAdapterService;
import org.javaboy.vhr.util.设计模式.适配器模式.需要适配的mq和接口.service.OrderService;

/**
 * 内部订单，判断首单逻辑
 */
public class InsideOrderServiceImpl implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1;
    }

}
