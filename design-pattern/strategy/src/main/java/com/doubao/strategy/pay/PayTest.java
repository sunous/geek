package com.doubao.strategy.pay;

/**
 * Created by Administrator on 2019/5/6.
 */
public class PayTest {

    public static void main(String[] args) {
        Payment payment = PayStrategy.get(PayStrategy.WECHATPAY);
        ResultMsg pay = payment.pay("123", 500);
        System.out.println(pay);


    }
}
