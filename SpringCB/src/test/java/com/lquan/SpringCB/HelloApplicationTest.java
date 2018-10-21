package com.lquan.SpringCB;

import com.didispace.web.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * ClassName HelloApplicationTest
 *
 * @Author lquan
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = HelloController.class)
//@WebAppConfiguration
public class HelloApplicationTest {
//    private MockMvc mvc;// 用于模拟对controller的调用
//
//    @Before
//    public void setUp()  throws Exception{
//        this.mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
//    }
//
//    @Test
//    public void hello() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("hello World")));
//       //String aa=  mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)).;
//       // System.out.print("^^^^^^^^^^^^^^^^^^^^^^^^^^^"+aa);
//    }
}
