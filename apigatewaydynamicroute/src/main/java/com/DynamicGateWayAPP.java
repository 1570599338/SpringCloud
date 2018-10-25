package com;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * ClassName DynamicGateWay
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@EnableZuulProxy
@SpringCloudApplication
public class DynamicGateWayAPP {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DynamicGateWayAPP.class).web(true).run(args);
    }
}
