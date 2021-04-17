package com.book.account.auth.repository.impl;

import javax.persistence.EntityManager;

import static com.book.account.auth.model.QUserAuth.userAuth;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.repository.custom.UserAuthRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserAuthRepositoryImpl implements UserAuthRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserAuthRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserAuth findByUserId(Long userId) {
        return queryFactory.selectFrom(userAuth).where(userAuth.user.userId.eq(userId)).fetchOne();
    }

}
