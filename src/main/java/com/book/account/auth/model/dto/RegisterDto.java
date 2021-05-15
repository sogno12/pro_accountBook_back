package com.book.account.auth.model.dto;

import com.book.account.auth.model.UserAuth;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst.Status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {

    private String loginId;
    private String userName;
    private String loginPwd;
    private String email;


	public User toUserEntity() {
        return User.builder()
                    .userName(this.userName)
                    .email(this.email)
                    .build();
	}
	public UserAuth toUserAuthEntity() {
		return UserAuth.builder()
                        .loginId(this.loginId)
                        .loginPwd(this.loginPwd)
                        .status(Status.ACTIVE)
                        .build();
	}

}