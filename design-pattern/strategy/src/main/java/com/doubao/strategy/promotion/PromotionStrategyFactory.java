package com.doubao.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/6.
 */
public class PromotionStrategyFactory {

    private static Map<String,PromotionStrategy> promotionMap = new HashMap<>();

    static {
        promotionMap.put(PromotionKey.CASHBACK,new CashbackStrategy());
        promotionMap.put(PromotionKey.GROUPBY,new GroupByStrategy());
    }

    private static final PromotionStrategy NON_PROMOTION = new NonStrategy();

    /**
     * 根据策略key获取活动的策略调用
     * @param promotionKey
     * @return
     */
    public static PromotionStrategy getPromotionStrategy(String promotionKey){
        PromotionStrategy promotionStrategy = promotionMap.get(promotionKey);
        return promotionStrategy == null?NON_PROMOTION:promotionStrategy;
    }

    public  interface  PromotionKey{
        String CASHBACK="cashback";
        String GROUPBY="groupby";
    }

}
