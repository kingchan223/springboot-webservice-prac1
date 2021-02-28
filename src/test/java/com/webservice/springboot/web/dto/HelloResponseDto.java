package com.webservice.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//선언된 모든 필드의 get메소드 생성.
@RequiredArgsConstructor//선언된 모든 final필드가 포함된 생성자를 생성해준다. (final 없는 필드는 nono)
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
