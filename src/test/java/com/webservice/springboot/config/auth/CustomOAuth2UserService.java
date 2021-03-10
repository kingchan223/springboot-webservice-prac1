package com.webservice.springboot.config.auth;

import com.webservice.springboot.config.auth.dto.OAuthAttributes;
import com.webservice.springboot.config.auth.dto.SessionUser;
import com.webservice.springboot.domain.user.User;
import com.webservice.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();//registrationId: 현재 로그인 진행중인 서비스를 구분하는 코드. 네이버 로그인 연동인지 구글 로그인인지 구분.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();//userNameAttributeName:oauth2로그인 시 키가 되는 필드값.PK와 같은 의미.
        //구글은 기본적으로 기본코드 "sub"를 지원. 네이버 카카오는 지원안함. 이후 네이버로그인과 구글 로그인을 동시 지원할 때 사용된다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스. 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용.
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));//SessionUser: 세션에 사용자 정보를 저장하기 위한 Dto클래스. 왜 User클래스를 쓰지 않고 새로 만들어 쓰는 이유는?
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),attributes.getAttributes(),attributes.getNameAttributesKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity->entity.update(attributes.getName(), attributes.getPicture())).orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
