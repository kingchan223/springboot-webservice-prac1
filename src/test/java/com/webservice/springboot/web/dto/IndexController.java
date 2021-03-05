package com.webservice.springboot.web.dto;

import com.webservice.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")// 머스테치 스타터덕에 앞의 경로와 귀의 확장자는 자동으로 붙는다.(ViewResolver가 처리)
    public String index(Model model){// postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";//.addAttribute: view에 전달할 데이터를 key:value쌍으로 전달가능.
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);// postService.findById는 entity를 dto로 만들어서 반환해준다.
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
