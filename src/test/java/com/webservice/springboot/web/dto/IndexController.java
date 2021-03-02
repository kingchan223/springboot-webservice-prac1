package com.webservice.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")// 머스테치 스타터덕에 앞의 경로와 귀의 확장자는 자동으로 붙는다.(ViewResolver가 처리)
    public String index(){
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
