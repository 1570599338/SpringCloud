package com.lquan.controller;

import com.lquan.entity.dto.User;
import com.lquan.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName RefactorHelloController
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
public class RefactorHelloController  implements HelloService{

    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello-refactor"+name;
    }

    @Override
    public User hello(@RequestHeader("name") String name,@RequestHeader("age")  Integer age) {
       name = name+"refactor";
        return new User(age,name);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello-refactor"+user.getName()+"，年龄"+user.getAge();
    }
}
