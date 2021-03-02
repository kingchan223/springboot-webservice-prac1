package com.webservice.springboot.domain.posts;

import com.webservice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내의 모든 필드의 Getter 메소드를 자동 생성. +Entity클래스에는 setter를 절대 만들지 않는다!!!
@NoArgsConstructor // 기본 생성자 자동 추가.
@Entity // 테이블과 링크될 클래스임을 나타낸다. 엔티티임을 알려준다.
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK생성 규칙을 나타낸다.
    private Long id;

    @Column(length=500, nullable = false)// 테이블의 컬럼을 나타낸다.
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    private String author;// 위에 칼럼 어노테이션을 붙이지 않아도 @Entity클래스의 모든 필드는 칼럼이 된다. 다만 특별한 옵션이 필요할 떄 쓴다.

    @Builder// 해당  클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함한다.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
