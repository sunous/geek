package com.doubao.strategy.pay;

/**
 * Created by Administrator on 2019/5/6.
 */
public class ResultMsg {

    private int code;
    private String msg;
    private Object data;

    public ResultMsg(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }
}

