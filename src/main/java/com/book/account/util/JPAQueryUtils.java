package com.book.account.util;

import com.book.account.user.model.dto.UserDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.data.domain.Sort;

/**
 * JPA 관련 유틸 클래스
 */
public class JPAQueryUtils {

    private JPAQueryUtils() {
        throw new IllegalStateException("JPAQueryUtils");
    }

    public static void queryDslGenOrderBy(EntityPathBase entity, Sort.Order o, JPAQuery<UserDto> query) {
        String fieldName = o.getProperty();
        try {
            entity.getClass().getDeclaredField(fieldName);
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
            new PathBuilder(entity.getClass(), entity.getMetadata()).get(fieldName)));
        } catch (NoSuchFieldException | SecurityException e) {

        }
    
    }

 
}
