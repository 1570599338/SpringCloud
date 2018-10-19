package com.lquan.inter;

import com.lquan.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 *这里的服务不区分大小写，所以使用hello-service和HELLO-SERVICE都可以
 *HELLO-SERVER-ADMIN
 * hello-server-admin
 */
@FeignClient("hello-server-admin/admin")
public interface IHelloService {

    @RequestMapping("/hello")
    String hello();


    @RequestMapping("/hello1")
    String hello(@RequestParam("name") String name);


    @RequestMapping("/hello2")
    String hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age);


    @RequestMapping("/hello3")
    String hello(@RequestBody User user);


}
