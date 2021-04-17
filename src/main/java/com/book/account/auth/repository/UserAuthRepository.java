package com.book.account.auth.repository;

import javax.transaction.Transactional;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.repository.custom.UserAuthRepositoryCustom;
import com.book.account.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String>, UserAuthRepositoryCustom {

    @Modifying
    @Transactional
    @Query("delete from UserAuth ua where ua.user=:user")
    void deleteByUser(User user);

}
