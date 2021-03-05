package com.webservice.springboot.web;

import com.webservice.springboot.service.posts.PostsService;
import com.webservice.springboot.web.dto.PostsResponseDto;
import com.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        //System.out.println("postsService.save(requestDto) : "+postsService.save(requestDto));
        return postsService.save(requestDto);// postsService.save(requestDto) == 게시물의 id값.(Long형)
    }
    // @RequestBody: Http 요청 몸체를 자바 객체로 전달받음. HTTP요청의 body내용을 자바 객체로 매핑하는 역할.
    // @ResponseBody: 자바객체를 HTTP몸체로 전송함. 자바객체 HTTP요청 body내용으로 매핑하는 역할.
    // @RequestBody PostsSaveRequestDto requestDto: 자바 객체로 받는 부분.
    // postsService.save(requestDto) : 여기서 postsService는 PostsRepository(JPA)를 변수로 가짐.
    // 또한 postsService는 Entity인 Posts도 가지고 있음.
    // 그래서 PostsRepository(JPA)의 save함수를 쓸수 있음.
    // 즉 Service에서 Dto객체를 entity로 바꾸고(toEntity),그 엔티티를 save함수의 인자로 넣어서 DB에 저장한다.

    @PutMapping("/api/v1/posts/{id}") //@PathVariable: post/{id}에서 전달 받을 것을  id에 넣어준다.
    // 그러면 마지막 return문에 postService.update(url로 받은 id, 자바객체로 바뀐 requestDto객체) 이렇게 반환된다.
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);//postsService.update(id, requestDto) == 수정된 게시글의 id.
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
