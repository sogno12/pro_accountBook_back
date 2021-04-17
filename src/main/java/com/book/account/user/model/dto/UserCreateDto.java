package com.book.account.user.model.dto;

import com.book.account.auth.model.UserAuth;
import com.book.account.common.model.BaseEntity;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst.Status;

import lombok.Data;

@Data
public class UserCreateDto extends BaseEntity {
    
    private String loginId;
    private String userName;
    private String email;
    private String loginPwd;


    public User toUserEntity() {
        return User.builder().userName(this.userName).email(this.email).build();
    }
    public UserAuth toUserAuthEntity() {
        return UserAuth.builder().loginId(this.loginId).loginPwd(this.loginPwd).status(Status.ACTIVE).build();
    }
}
