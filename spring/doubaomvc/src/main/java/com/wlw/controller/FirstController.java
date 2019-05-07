package com.wlw.controller;

import com.wlw.annotation.WlwAutowired;
import com.wlw.annotation.WlwController;
import com.wlw.annotation.WlwRequestMapping;
import com.wlw.service.FirstService;

@WlwController
@WlwRequestMapping("demo")
public class FirstController {

    @WlwAutowired
    private FirstService firstService;


    @WlwRequestMapping("query")
    public Object query(String name){

        return "my name is "+name;
    }


}
