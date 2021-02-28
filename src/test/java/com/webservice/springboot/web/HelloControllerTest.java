package com.webservice.springboot.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)//1.테스트를 실행할때 jUnit에 내장된 실행자 외에 다른 실행자를 실행.여기서는 SpringRunner라는 실행자 사용.
//2.즉 스프링 부트 테스트와 JUnit사이의 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class)//1.여러 스프링 테스트 어노테이션 중, Web에 집중할 수 있는 어노테이션.
//2.선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있음.
//단, @Service, @Component, @Repository등은 사용할 수 없
public class HelloControllerTest {

    @Autowired//스프링이 관리하는 빈을 주입받는다.
    private MockMvc mvc;//웹 API를 테스트할 때 사용, 스프링MVC테스트의 시작점임. 이 클래스를 통해HTTP GET, POST등에 대한 API를 테스트할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다.
                .andExpect(status().isOk())// mvc.perform의 결과를 검증
                .andExpect(content().string(hello));//mvc.perform의 결과를 검증
    }
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.amount", is(amount)));
    }
}
