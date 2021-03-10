package com.webservice.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//@Target: 이 어노테이션이 생성될 수 잇는 위치를 지정. PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체만 사용가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
