package org.javaboy.vhr.util.设计模式.工厂模式;

import java.util.Map;

/**
 * 发奖接口
 */
public interface ICommodity {
     void senCommodity(String UId, String CommodityId, String bizID, Map<String, String> extMap);//不需要需要方法体
}
