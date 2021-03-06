package com.webservice.springboot.config.auth.dto;

import com.webservice.springboot.domain.user.Role;
import com.webservice.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributesKey, String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    // of(): OAuth2User에서 반환하는 사용자 정보는 Map이기 떄문에 값 하나하나를 변환해야한다.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }

    // User엔티티를 생성
    // OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 떄 임.
    // OAuthAttributes 클래스 생성이 끝났으면 같은 패키지 SessionUser클래스를 생성합니다.
    public User toEntity(){//User 엔티티 생성
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)// 가입할 때의 기본 권한을 GUEST로 주기 위해서 role빌더값에는 Role.GUEST를 사용합니다.
                .build();

    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributesKey(userNameAttributeName)
                .build();
    }
}
