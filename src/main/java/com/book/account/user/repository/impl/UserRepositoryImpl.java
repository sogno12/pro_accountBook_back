package com.book.account.user.repository.impl;

import static com.book.account.user.model.QUser.user;
import static com.book.account.auth.model.QUserAuth.userAuth;

import java.util.List;

import javax.persistence.EntityManager;

import com.book.account.user.model.consts.UserConst.Status;
import com.book.account.user.model.dto.UserDto;
import com.book.account.user.repository.custom.UserRepositoryCustom;
import com.book.account.util.JPAQueryUtils;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<UserDto> getUsers(UserDto userDto, Pageable pageable) {
        JPAQuery<UserDto> query = queryFactory
                .select((Projections.fields(UserDto.class, user.userId, user.userName, user.email, userAuth.loginId,
                        userAuth.status)))
                .from(user).leftJoin(userAuth).on(user.userId.eq(userAuth.user.userId))
                .where(userNameLike(userDto.getUserName()), loginIdLike(userDto.getLoginId()), statusEq(userDto.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            JPAQueryUtils.queryDslGenOrderBy(user, o, query);
        }

        QueryResults<UserDto> results = query.fetchResults();
        List<UserDto> content = results.getResults();
        Long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public UserDto getUser(Long userId) {
        UserDto userDto = queryFactory
                .select((Projections.fields(UserDto.class, user.userId, user.userName, user.email, userAuth.loginId,
                        userAuth.status)))
                .from(user).leftJoin(userAuth).on(user.userId.eq(userAuth.user.userId)).where(user.userId.eq(userId))
                .fetchOne();

        return userDto;
    }

    private BooleanExpression userNameLike(String userName) {
        return userName != null ? user.userName.lower().like("%" + userName.toLowerCase() + "%") : null;
    }

    private BooleanExpression loginIdLike(String loginId) {
        return loginId != null ? userAuth.loginId.lower().like("%" + loginId.toLowerCase() + "%") : null;
    }

    private BooleanExpression statusEq(Status status) {
        return status != null ? userAuth.status.eq(status) : null;
    }

}
