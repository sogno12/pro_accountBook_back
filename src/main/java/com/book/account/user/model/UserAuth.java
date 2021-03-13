package com.book.account.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;

import lombok.Getter;

@Entity
@Table(name = "UP_USER_AUTL")
@Getter
public class UserAuth extends BaseEntity {
    
    @Id
    private String loginId;
    private String loginPwd;
    private Long userId;
}
