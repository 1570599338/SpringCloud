package com.didispace.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName HelloController
 *
 * @Author lquan
 * @Description 测试返回restful
 * @Date
 * @Param
 * @return
 *
 **/
@Slf4j
@RestController    //1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
public class HelloController {

    @Autowired
    private DiscoveryClient client;


    @RequestMapping("/hello")
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();

        System.out.println("/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello World";
    }
}
