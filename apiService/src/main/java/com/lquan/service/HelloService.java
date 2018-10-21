package com.lquan.service;

import com.lquan.entity.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName HelloService
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@RequestMapping("/refactor")
public interface HelloService {


    @RequestMapping("/hello4")
    String hello(@RequestParam("name") String name);


    @RequestMapping("/hello5")
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);


    @RequestMapping("/hello6")
    String hello(@RequestBody User user);



}
