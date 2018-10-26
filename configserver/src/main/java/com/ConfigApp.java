package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ClassName ConfigApp
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/

@EnableConfigServer
@SpringBootApplication
public class ConfigApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigApp.class).web(true).run(args);
    }
}
