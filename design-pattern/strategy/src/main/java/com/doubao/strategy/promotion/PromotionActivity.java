package com.doubao.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/6.
 */
public class PromotionActivity {
    public PromotionStrategy promotionStrategy;

    public  PromotionActivity(PromotionStrategy promotionStrategy){
        this.promotionStrategy=promotionStrategy;
    }

    public void excute(){
        promotionStrategy.doPromotion();
    }


}
