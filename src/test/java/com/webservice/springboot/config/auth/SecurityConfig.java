package com.webservice.springboot.config.auth;

import com.webservice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정 활성화
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console화면을 사용하기 위해 해당 옵션들을 disable해준다.
                .and()
                    .authorizeRequests()//Url별 권한 관리를 설정하는 옵션의 시작점.authorizeRequests가 선언되야 antMatchers옵션으로 사용가능
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//"/api/v1/**"주소를 가진 API는 User권한을 가진 사람만 가능하도록.
                    .anyRequest().authenticated()//anyRequest(): 설정된 값들 외에 나머지 url은 anyRequest()를 사용하여 인증된 사용자들에게만 허용
                .and()
                    .logout()//로그아웃 설정의 진입점
                        .logoutSuccessUrl("/")//로그아웃 성공시 "/"로 진입
                .and()
                    .oauth2Login()// oauth2로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()//oauth2로그인 성공시 사용자 정보를 가져올 때의 설정들을 담당.
                            .userService(customOAuth2UserService);//소셜 로그인 성공시 후속 조치를 진행할 UserService인터페이스의 구현체를 등록.
                                                                    // 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시가능
    }
}
