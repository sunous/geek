package com.wlw.demo;

import com.wlw.annotation.WlwService;

@WlwService
public class DemoServiceImpl implements DemoService {
    @Override
    public String getName() {
        return "测试demo!";
    }
}
