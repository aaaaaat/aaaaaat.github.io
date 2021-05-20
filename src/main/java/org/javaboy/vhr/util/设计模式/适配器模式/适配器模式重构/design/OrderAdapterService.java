package org.javaboy.vhr.util.设计模式.适配器模式.适配器模式重构.design;

/**
 * 营销活动会修改，不再只是接入Mq就发放奖励
 * 因为拉新的数量越来越多，需要做一些限制
 * 因此增加了只有首单用户才发放奖励，这是就需要对这种方式进行限制，而此时mq中并没有判断首单的属性。
 * 只能通过接口进行查询
 * 1、查询订单数量 出参long
 * 2、查询是否首单：出参boolean
 *
 * --》通过适配器的模式实现
 * 接口的实现需要完成此接口定义的方法，并把具体的逻辑包装到指定的类中，满足单一指责
 */
public interface OrderAdapterService {

    boolean isFirst(String uId);

}
