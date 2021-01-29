package com.book.account.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst.AuthType;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UP_USER_AUTH")
public class UserAuth extends BaseEntity {

    @Id
    private String loginId;
    private String loginPwd;

    @ColumnDefault("USER")
    @Enumerated(value = EnumType.STRING)
    private AuthType authType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}
