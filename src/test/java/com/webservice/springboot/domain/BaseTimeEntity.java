package com.webservice.springboot.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass// JPA엔티티 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createDate,modifiedDate)도 칼럼으로 인식하도록한다.
@EntityListeners(AuditingEntityListener.class)// BaseTimeEntity클래스에 auditing기능을 포함시킨다.
public abstract class BaseTimeEntity {

    @CreatedDate//entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createDate;

    @LastModifiedDate//조회환 entity의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
    //그리고 Posts클래스가 BaseTimeEntity를 상속받도록한다.
}
