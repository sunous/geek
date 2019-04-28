package com.doubao.proxy.staticed;

public class Father {

    private Son son;

    //静态代理，要指定被代理对象，不易扩展
    public Father(Son son){
        this.son= son;
    }
    //目标对象的引用拿到
    public void findLove(){
        System.out.println("父母物色对象");
        this.son.findLove();
        System.out.println("合适，结婚");

    }

}
