package com.doubao.strategy.pay;

/**
 * Created by Administrator on 2019/5/6.
 */
public abstract class Payment {

    public abstract String getName();

    public ResultMsg pay(String uid,double amount){
        if (amount>500){
            return new ResultMsg(500,"用户"+uid+"使用"+getName()+"支付失败","消费"+amount);
        }
        return new ResultMsg(200,"用户"+uid+"使用"+getName()+"支付成功","消费"+amount);
    }


}
