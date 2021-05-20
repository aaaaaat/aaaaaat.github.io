package org.javaboy.vhr.util.设计模式.工厂模式;

/**
 * TODO 创建商店工厂
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/18 5:08 下午
 */
public class storeFactory {
    /**
     *
     * @param clazz 奖品对象
     * @return 实例化对象
     * @author xyf
     * @date 2021/5/18 5:09 下午
     * @return
     */
    public ICommodity getCommodityService(Class<? extends ICommodity> clazz) throws IllegalAccessException, InstantiationException {

        if (null==clazz) return null;

        //返回 传入Class 对应实例newInstance
        return clazz.newInstance();
    }
}
