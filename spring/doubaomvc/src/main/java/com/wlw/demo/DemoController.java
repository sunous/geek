package com.wlw.demo;

import com.wlw.annotation.WlwAutowired;
import com.wlw.annotation.WlwController;
import com.wlw.annotation.WlwRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WlwController
@WlwRequestMapping
public class DemoController {

    @WlwAutowired
    private DemoService demoService;


    @WlwRequestMapping("query")
    public void query(HttpServletRequest request, HttpServletResponse response,String name) throws Exception{


        response.getWriter().write(demoService.getName()+" name is "+name);
    }

}
