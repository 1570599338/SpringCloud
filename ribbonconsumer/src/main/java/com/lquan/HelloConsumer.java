package com.lquan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName HelloConsumer
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@RestController
public class HelloConsumer {
    private  String url ="http://hello-server-admin:8080/admin/hello";

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value="/consumer",method = RequestMethod.GET)
    public String helloConsumer() {
        String body  = restTemplate.getForEntity(url,String.class).getBody();
        return body;
    }

}
