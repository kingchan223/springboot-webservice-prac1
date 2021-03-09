package com.webservice.springboot.domain.user;

/* 사용자 정보를 담당하는 도메인인 User클래스 */
import com.webservice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)//JPA로 DB에 저장할 때 Enum값을 어떤형태로 저장할지 결정. 기본은 int라서 숫자로 저장되면 DB로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수 없기에 문자열로 저장할 수 있게 지정.
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture= picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
