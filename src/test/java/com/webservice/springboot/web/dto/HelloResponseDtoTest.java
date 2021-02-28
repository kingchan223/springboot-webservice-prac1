package com.webservice.springboot.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Getter//선언된 모든 필드의 get메소드 생성.
@RequiredArgsConstructor//선언된 모든 final필드가 포함된 생성자를 생성해준다. (final 없는 필드는 nono)
public class HelloResponseDtoTest {
    @Test
    public void 롬봄_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,
                                                amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
