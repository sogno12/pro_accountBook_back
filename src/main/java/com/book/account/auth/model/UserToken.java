package com.book.account.auth.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "UP_USER_TOKEN")
@NoArgsConstructor
public class UserToken {

    @Id
    private Long userId;

    private String token;

    private Date expiredAt;

    private String ip;
    
    private LocalDateTime updatedAt;

    public UserToken(Long userId) {
        this.userId = userId;
    }

    public void setUpdate(String accessToken, Date expiresIn, String ip) {
        this.token = accessToken;
        this.expiredAt = expiresIn;
        this.ip = ip;
        this.updatedAt = LocalDateTime.now();
    }

}