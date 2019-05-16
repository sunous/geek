package com.doubao.thread;


public class APP {

    private static IRequestProcessor iRequestProcessor;


    public APP(){
        PrintProcessor printProcessor = new PrintProcessor();
        printProcessor.start();
        //定义责任链
        iRequestProcessor = new SaveProcessor(printProcessor);
        ((SaveProcessor)iRequestProcessor).start();
    }




    public static void main(String[] args) {

        APP app = new APP();
        Request request=new Request();
        request.setName("douBao");
        iRequestProcessor.process(request);


    }
}
