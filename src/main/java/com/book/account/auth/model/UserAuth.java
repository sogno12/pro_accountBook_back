package com.book.account.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.user.model.User;

import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name = "UP_USER_AUTH")
public class UserAuth extends BaseEntity {
    
    @Id
    private String loginId;
    private String loginPwd;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}
