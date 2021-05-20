package org.javaboy.vhr.util.设计模式.享元模式;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂：通过Map结构存放已经从库表或接口中查询到的数据并存放到内存中，方便下次直接获取。
 * 这种结构在编程开发中比较常见，有时也为了保证分布式系统部署能获取到信息，会把数据存放到redis中
 */
public class ActivityFactory {

    static Map<Long, Activity> activityMap = new HashMap<Long, Activity>();

    public static Activity getActivity(Long id) {

        Activity activity = activityMap.get(id);

        if (null == activity) {
            // 模拟从实际业务应用从接口中获取活动信息
            activity = new Activity();
            activity.setId(10001L);
            activity.setName("图书嗨乐");
            activity.setDesc("图书优惠券分享激励分享活动第二期");
            activity.setStartTime(new Date());
            activity.setStopTime(new Date());
            activityMap.put(id, activity);
        }
        return activity;
    }

}
