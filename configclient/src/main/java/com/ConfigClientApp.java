package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ClassName ConfigClientApp
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@SpringBootApplication
public class ConfigClientApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientApp.class).web(true).run(args);
    }

}
