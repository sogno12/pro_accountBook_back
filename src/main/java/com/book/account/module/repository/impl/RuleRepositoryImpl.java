package com.book.account.module.repository.impl;

import static com.book.account.module.model.QRule.rule;

import javax.persistence.EntityManager;

import com.book.account.module.model.dto.RuleDto;
import com.book.account.module.repository.custom.RuleRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RuleRepositoryImpl implements RuleRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    public RuleRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }
    
    @Override
    public RuleDto getRule(String ruleId) {
        JPAQuery<RuleDto> query = queryFactory.select(
            Projections.fields(RuleDto.class, 
            rule.ruleId, rule.ruleName,
            rule.moduleId, rule.sortNo
            )
        ).from(rule).where(rule.ruleId.eq(ruleId))
        .orderBy(rule.sortNo.asc());

        RuleDto ruleDto = query.fetchOne();

        return ruleDto;
    }
    
}
