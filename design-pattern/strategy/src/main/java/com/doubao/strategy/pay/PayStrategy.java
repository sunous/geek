package com.doubao.strategy.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/6.
 */
public class  PayStrategy {

    public static String ALIPAY="aliPay";
    public static String WECHATPAY="wechatPay";

   public static Map<String,Payment> paymentMap ;

   static {
       paymentMap = new HashMap<>();
       paymentMap.put(ALIPAY,new AliPay());
       paymentMap.put(WECHATPAY,new WechatPay());
   }

    public static Payment get(String payKey){
        if (!paymentMap.containsKey(payKey)){
            return null;
        }
        return paymentMap.get(payKey);

    }

}
