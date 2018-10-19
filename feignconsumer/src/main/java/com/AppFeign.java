package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * ClassName AppFeign
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AppFeign {
    public static void main(String[] args) {
        SpringApplication.run(AppFeign.class,args);
    }
}
