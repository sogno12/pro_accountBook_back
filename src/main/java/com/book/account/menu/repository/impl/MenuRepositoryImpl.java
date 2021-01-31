package com.book.account.menu.repository.impl;

import static com.book.account.menu.model.QMenuView.menuView;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import com.book.account.menu.model.MenuView;
import com.book.account.menu.model.dto.MenuDto;
import com.book.account.menu.repository.custom.MenuRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MenuRepositoryImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MenuView> getMenuList(Long userId) {
       
        JPAQuery<MenuView> query = queryFactory
                .select(Projections.fields(
                        MenuView.class, 
                
                        menuView.menuId,
                        menuView.menuName,
                        menuView.sortNo

                ))
                .from(menuView)
                .where(menuView.userId.in(0L, userId))
                .orderBy(menuView.sortNo.asc())
        ;
        List<MenuView> result = query.fetch();
        return result;
    }
    
}
