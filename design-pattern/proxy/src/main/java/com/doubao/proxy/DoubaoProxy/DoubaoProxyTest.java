package com.doubao.proxy.DoubaoProxy;

public class DoubaoProxyTest {


    public static void main(String[] args) throws Exception {

        BenChi obj = (BenChi)new DoubaoCarProxy().getInstance(new BenChi());
        System.out.println(obj.getClass());
        System.out.println(obj.getName());
//        System.out.println(((BenChi)obj).getName());
//        Method method = obj.getClass().getMethod("getName",null);
//        method.invoke(obj);
    }
}
