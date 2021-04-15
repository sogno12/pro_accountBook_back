package com.book.account.user.repository;

import java.util.Optional;

import com.book.account.user.model.User;
import com.book.account.user.repository.custom.UserRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    
    Optional<User> findByUserId(Long userId);
}
