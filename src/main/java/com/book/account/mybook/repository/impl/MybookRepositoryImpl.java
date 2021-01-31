package com.book.account.mybook.repository.impl;

import javax.persistence.EntityManager;

import com.book.account.mybook.repository.custom.MybookRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MybookRepositoryImpl implements MybookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MybookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Long countByIdAndIsUsed(Long userId) {
        // TODO 사용자의 가계부 갯수 가져오기
        return 0L;
    }

}
