package com.lquan.controller;

import com.lquan.entity.User;
import com.lquan.inter.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName FeignConsumerController
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/

@RestController
public class FeignConsumerController {

    @Autowired
    IHelloService helloService;

    @RequestMapping(value = "feign-consumer",method = RequestMethod.GET)
    public  String helloFeign(){
        return helloService.hello();
    }


    @RequestMapping(value = "feign-consumer2",method = RequestMethod.GET)
    public  String helloFeign2(){
        StringBuffer sb = new StringBuffer();
        sb.append(helloService.hello()).append("\n");
        sb.append(helloService.hello("----DIDI")).append("\n");
        sb.append(helloService.hello("******DIDI",30)).append("\n");
        sb.append(helloService.hello(new User(30,"+++++++LQUAN"))).append("\n");

        return sb.toString();
    }



}
