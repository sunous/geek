package com.doubao.strategy.promotion;

/**
 * Created by Administrator on 2019/5/6.
 */
public class PromotionStrategyTest {

    public static void main(String[] args) {

        PromotionActivity activity618 = new PromotionActivity(new CashbackStrategy());
        PromotionActivity activity1111 = new PromotionActivity(new GroupByStrategy());
        activity618.excute();
        activity1111.excute();


//        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(PromotionStrategyFactory.PromotionKey.CASHBACK);
//        promotionStrategy.doPromotion();
    }
}
