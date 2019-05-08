package com.doubao.strategy.pay;

/**
 * Created by Administrator on 2019/5/6.
 */
public class WechatPay extends Payment{

    @Override
    public String getName() {
        return "微信";
    }

}
