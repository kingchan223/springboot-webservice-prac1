package com.webservice.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 여기사 메인 클래스. 항상 모든 파일의 최상위에 위치해야한다.
@EnableJpaAuditing // JpaAuditing활성화
@SpringBootApplication//스프링부트의 자동설정, 스프링Bean 읽기와 생성을 모두 자동으로 설정함.
public class Application { //앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);//Web Application Server를 자동으로 실행.
    }//이렇게 하면 항상 서버에 톰캣을 설치할 필요가 없고 스프링부트로 만들어진 Jar파일로 실행하면 된다.
}
