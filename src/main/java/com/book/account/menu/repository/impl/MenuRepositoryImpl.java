package com.book.account.menu.repository.impl;

import static com.book.account.menu.model.QMenu.menu;

import java.util.List;

import javax.persistence.EntityManager;

import com.book.account.menu.model.dto.MenuDto;
import com.book.account.menu.repository.custom.MenuRepositoryCustom;
import com.querydsl.core.QueryResults;
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
    public MenuDto getMenu(String menuId) {
        JPAQuery<MenuDto> query = queryFactory.select(
            Projections.fields(MenuDto.class, 
            menu.menuId, menu.menuName, 
            menu.upMenuId, menu.sortNo, 
            menu.menuPath, menu.menuIcon, 
            menu.description, menu.menuType)
        ).from(menu)
        .where(menu.menuId.eq(menuId))
        ;

        MenuDto menuDto = query.fetchOne();
        return menuDto;
    }

    @Override
    public List<MenuDto> getMenus() {
        JPAQuery<MenuDto> query = queryFactory.select(
            Projections.fields(MenuDto.class,
               menu.menuId, menu.menuName, 
            menu.upMenuId, menu.sortNo, 
            menu.menuPath, menu.menuIcon, 
            menu.description, menu.menuType)
        ).from(menu)
        .orderBy(menu.sortNo.asc())
        ;
        QueryResults<MenuDto> result = query.fetchResults();
        List<MenuDto> menus = result.getResults();
        return menus;
    }
    
}
