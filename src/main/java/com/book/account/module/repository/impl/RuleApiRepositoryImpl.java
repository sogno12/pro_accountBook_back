package com.book.account.module.repository.impl;

import static com.book.account.module.model.QApi.api;
import static com.book.account.module.model.QRule.rule;
import static com.book.account.module.model.QRuleApi.ruleApi;

import javax.persistence.EntityManager;

import com.book.account.module.model.dto.RuleApiDto;
import com.book.account.module.repository.custom.RuleApiRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RuleApiRepositoryImpl implements RuleApiRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RuleApiRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public RuleApiDto findApibyRuleIdAndApiId(String ruleId, String apiId) {
        JPAQuery<RuleApiDto> query = queryFactory.select((
            Projections.fields(
                    RuleApiDto.class, ruleApi.ruleApiId, api.apiId, api.description, rule.ruleId, rule.ruleName)
        )).from(ruleApi)
        .leftJoin(api).on(ruleApi.apiId.eq(api.apiId))
        .leftJoin(rule).on(ruleApi.ruleId.eq(rule.ruleId))
        .where(ruleApi.ruleId.eq(ruleId).and(ruleApi.apiId.eq(apiId)));

        RuleApiDto apiDto = query.fetchOne();

        return apiDto;
    }
    
    
}
