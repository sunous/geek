package com.wlw.service;

import com.wlw.annotation.WlwService;

@WlwService
public class FirstServiceImpl implements FirstService {

    @Override
    public void getName() {
        System.out.println("first service impl name");
    }

}
