package com.book.account.module.repository.impl;

import static com.book.account.module.model.QModule.module;

import javax.persistence.EntityManager;

import com.book.account.module.model.dto.ModuleDto;
import com.book.account.module.repository.custom.ModuleRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ModuleRepositoryImpl implements ModuleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ModuleRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ModuleDto getModule(String moduleId) {
        JPAQuery<ModuleDto> query = queryFactory.select(
                Projections.fields(ModuleDto.class,
            module.moduleId, module.moduleName, module.sortNo
        )).from(module).where(module.moduleId.eq(moduleId));

        ModuleDto moduleDto = query.fetchOne();
        return moduleDto;
    }
    
}
