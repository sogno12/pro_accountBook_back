package com.book.account.common.model;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class BaseEntity extends BaseCreatedEntity {

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 1_1. @MappedSuperClass - 부모 클래스는 테이블과 매핑하지 않고 상속받는 자식 클래스에 매핑 정보만 제공하고 싶을 때 사용한다.
    // 1_2. @EntitiyListeners(AuditingEntitiyListner.class) - Entity를 DB에 적용하기 이전 또는 이후에
    // 커스텀 콜백을 요청할 수 있는 annotation.

}
