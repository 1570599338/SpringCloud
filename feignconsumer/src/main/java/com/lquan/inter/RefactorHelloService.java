package com.lquan.inter;

//import com.lquan.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("hello-server-admin/admin")
public interface RefactorHelloService //extends HelloService
{

}
