package com.doubao.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements IRequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    private  IRequestProcessor nextProcessor;

    private volatile boolean isFinish=false;

    public SaveProcessor(){

    }

    public SaveProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void process(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while(!isFinish){
            try {
                Request request = requests.take();
                //真正的处理逻辑
                System.out.println("SaveProcessor:"+request);
                if (null != nextProcessor){
                    nextProcessor.process(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
