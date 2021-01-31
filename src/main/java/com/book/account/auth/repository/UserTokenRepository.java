package com.book.account.auth.repository;

import com.book.account.auth.model.UserToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    
}
