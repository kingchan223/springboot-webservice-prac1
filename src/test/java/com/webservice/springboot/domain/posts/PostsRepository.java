package com.webservice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// Repository = DB Layer 접근자 라고도 불린다.
// 단순히 paRepository<Posts, Long>를 상속하면 기본적인 CRUD메소드가 자동으로 생성된다.
// 주의할 점은 Entity 클래스와 Entity Repository는 함께 위치해야한다.
// 둘은 아주 밀접한 관계이고, Entity 클래스는 기본 Repository가 없으면 제대로 역할을 못한다.
// 둘은 도메인 패키지에서 함께 관리한다.
public interface PostsRepository extends JpaRepository<Posts, Long>{
}
