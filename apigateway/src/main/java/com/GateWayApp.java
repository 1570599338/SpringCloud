package com;

import com.lquan.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * ClassName getWayApp
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@EnableZuulProxy
@SpringCloudApplication
public class GateWayApp {
    public static void main(String[] args) {

        new SpringApplicationBuilder(GateWayApp.class).web(true).run(args);

    }

    @Bean
    public AccessFilter accessFilter(){
        return  new AccessFilter();
    }
}
