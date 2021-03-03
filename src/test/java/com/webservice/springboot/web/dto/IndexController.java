package com.webservice.springboot.web.dto;

import com.webservice.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")// 머스테치 스타터덕에 앞의 경로와 귀의 확장자는 자동으로 붙는다.(ViewResolver가 처리)
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";//.addAttribute: view에 전달할 데이터를 key:value쌍으로 전달가능.
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
