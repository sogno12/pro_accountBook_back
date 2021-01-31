package com.book.account.auth.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AuthenticationBean {

    //JWT
    private Long userId;
    private String userName;
    private String loginId;
    private String tokenType;
    private String accessToken;
    private Date expiresIn;
    
}
