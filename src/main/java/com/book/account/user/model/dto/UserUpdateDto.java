package com.book.account.user.model.dto;

import com.book.account.user.model.consts.UserConst.Status;

import lombok.Data;

@Data
public class UserUpdateDto {

    private Long userId;

    // USER
    private String userName;
    private String email;

    // USER_AUTH
    private Status status;


    private Long updatedBy;
    
}
