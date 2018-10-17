package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName App
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/

@EnableEurekaServer//  启动一个服务注册中心
@SpringBootApplication
public class App {
    public static void main(String[] args) {

        new SpringApplicationBuilder(App.class).web(true).run(args);

    }
}
