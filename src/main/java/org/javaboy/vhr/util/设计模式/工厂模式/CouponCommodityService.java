package org.javaboy.vhr.util.设计模式.工厂模式;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * TODO 优惠券
 *
 * @author xyf
 * @version 1.0
 * @date 2021/5/18 4:58 下午
 */
public class CouponCommodityService implements ICommodity{

    private static final Logger log = LoggerFactory.getLogger(CouponCommodityService.class);

    @Override
    public void senCommodity(String UId, String CommodityId, String bizID, Map<String, String> extMap) {
        //根据入参 UId、CommodityId、bizId
        //调用couponService（优惠券所用的service）发放优惠券 return CouponResult
        log.info("优惠券发放完毕");

        //if CouponResult.getCode() 不成功
        //throw new RuntimeException
    }
}
