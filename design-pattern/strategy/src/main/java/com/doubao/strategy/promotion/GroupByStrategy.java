package com.doubao.strategy.promotion;

/**
 * Created by Administrator on 2019/5/6.
 */
public class GroupByStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("满20人拼团，享受优惠价格");
    }
}
