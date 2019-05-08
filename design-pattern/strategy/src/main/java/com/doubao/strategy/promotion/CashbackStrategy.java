package com.doubao.strategy.promotion;

/**
 * Created by Administrator on 2019/5/6.
 */
public class CashbackStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("活动结束后，返还现金到支付宝账户");
    }
}
