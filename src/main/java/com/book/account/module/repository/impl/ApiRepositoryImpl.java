package com.book.account.module.repository.impl;

import static com.book.account.module.model.QApi.api;

import java.util.List;

import javax.persistence.EntityManager;

import com.book.account.module.model.consts.ApiConst.AccessScope;
import com.book.account.module.model.dto.ApiDto;
import com.book.account.module.repository.custom.ApiRepositoryCustom;
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

public class ApiRepositoryImpl implements ApiRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ApiRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ApiDto> getApis(ApiDto apiDto, Pageable pageable) {
        JPAQuery<ApiDto> query = queryFactory
                .select((Projections.fields(
                        ApiDto.class, api.apiId, api.httpType, api.url, api.description, api.accessScope)))
                .from(api)
                .where(apiDescripLike(apiDto.getDescription()), apiIdLike(apiDto.getApiId()), 
                        accessScopeEq(apiDto.getAccessScope()))
                .offset(pageable.getOffset()).limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            JPAQueryUtils.queryDslGenOrderBy(api, o, query);
        }

        QueryResults<ApiDto> results = query.fetchResults();
        List<ApiDto> content = results.getResults();
        Long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }

    @Override
    public ApiDto getApi(String apiId) {
        JPAQuery<ApiDto> query = queryFactory
                .select(Projections.fields(ApiDto.class, 
                api.apiId, api.httpType,
                api.description, api.url, api.accessScope))
                .from(api)
                .where(api.apiId.eq(apiId));
        ApiDto apiDto = query.fetchOne();
        return apiDto;
    }

    private BooleanExpression apiDescripLike(String description) {
        return description != null ? api.description.lower().like("%" + description.toLowerCase() + "%") : null;
    }

    private BooleanExpression apiIdLike(String apiId) {
        return apiId != null ? api.apiId.lower().like("%" + apiId.toLowerCase() + "%") : null;
    }

    private BooleanExpression accessScopeEq(AccessScope accessScope) {
        return accessScope != null ? api.accessScope.eq(accessScope) : null;
    }
    
}
