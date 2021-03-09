package com.webservice.springboot.web.dto;

import com.webservice.springboot.config.auth.dto.SessionUser;
import com.webservice.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private HttpSession httpSession;

    @GetMapping("/")// 머스테치 스타터덕에 앞의 경로와 귀의 확장자는 자동으로 붙는다.(ViewResolver가 처리)
    public String index(Model model){// postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달.
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser)httpSession.getAttribute("user");// this.httpSession is null 오류
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";//.addAttribute: view에 전달할 데이터를 key:value쌍으로 전달가능.
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")// url의 id를 변수로 받아서 그걸 View로 보낼때 Model객체를 사용함.
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);// postService.findById는 entity를 dto로 만들어서 반환해준다.
        model.addAttribute("post",dto);
        return "posts-update";
    }
    
    @GetMapping("/users/posts/{author}")
    public String usersPosts(@PathVariable String author, Model model){
        model.addAttribute("authors",postsService.findByAuthor(author));
        return "users-posts";
    }

}
