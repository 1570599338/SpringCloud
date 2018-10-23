package com.lquan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName GateWayDemo
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@RestController
public class GateWayDemo {

    @RequestMapping("/local/hello")
    public String hello(){

        return "Hello World Local";
    }
}
