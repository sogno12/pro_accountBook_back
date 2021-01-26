package com.book.account.auth.repository;

import com.book.account.auth.model.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {
    
}
