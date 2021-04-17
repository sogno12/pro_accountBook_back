package com.book.account.user.model.dto;

import com.book.account.user.model.consts.UserConst.Status;

import lombok.Data;

@Data
public class UserDto {

    private Long userId;
    private String userName;
    private String email;
    private String loginId;
    private Status status;
    
    
}
