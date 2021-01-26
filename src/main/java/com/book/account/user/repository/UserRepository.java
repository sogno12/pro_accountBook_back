package com.book.account.user.repository;

import com.book.account.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
