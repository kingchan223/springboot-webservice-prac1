package com.webservice.springboot.service.posts;


import com.webservice.springboot.domain.posts.Posts;
import com.webservice.springboot.domain.posts.PostsRepository;
import com.webservice.springboot.web.dto.PostsListResponseDto;
import com.webservice.springboot.web.dto.PostsResponseDto;
import com.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 얘가 서비스에서 자동 Bean등록역할을 해준다.
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional// @Transactional은 아래 함수를 하나의 서비스로 묶이게 해준다. 충돌시 자동으로 callback해줌.
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {//findById:매개변수로 받은 id를 기준으로 엔티티를 검색하고, 찾으면 그 엔티티를 반환해준다.
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        // 위 문장에서 posts는 엔티티이기 때문에 findById가 반환한 엔티티를 받을 수 있다.
        posts.update(requestDto.getTitle(), requestDto.getContent());
        // http에서 java객체로 변환된 requestDto를 update메소드의 인자로 넣어서 그 title과 content를 DB에 update한다.
        return id; // !!!!!!!! 굳이 return을 왜할까? 그게 궁금함.
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다!"+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){//findById:매개변수로 받은 id를 기준으로 엔티티를 검색하고, 찾으면 그 엔티티를 반환해준다.
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
                return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)//
    public List<PostsListResponseDto> findAllDesc(){
        //map(posts->new PostsListResponseDto(posts)) :postsRepository결과로 넘어온 Posts의 Stream을 map을 통해 PostsListRespnseDto변환 -> List로 반환하는 메소드.
        return postsRepository.findAllDesc().stream().map(posts->new PostsListResponseDto(posts)).collect(Collectors.toList());
    }
}
