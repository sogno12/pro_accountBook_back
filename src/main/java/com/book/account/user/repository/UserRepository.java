package com.book.account.user.repository;

import javax.transaction.Transactional;

import com.book.account.user.model.User;
import com.book.account.user.repository.custom.UserRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    
    /** USER_ID로 USER삭제
     * 1. deleteById로 삭제할 경우, select 후 delete 해서 벌크 연산에 불리함
     * 2. transactinal를 사용할 필요성
     */
    @Transactional
    @Modifying
    @Query("delete from User u where u.userId=:userId")
    void deleteByUserId(Long userId);
}
