package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName AppConsumer
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class AppConsumer {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(AppConsumer.class,args);
    }
}
