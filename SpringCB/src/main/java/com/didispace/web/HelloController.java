package com.didispace.web;

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

@RestController    //1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "hello World";
    }
}
