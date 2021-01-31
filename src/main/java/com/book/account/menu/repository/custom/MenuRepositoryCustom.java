package com.book.account.menu.repository.custom;

import java.util.List;

import com.book.account.menu.model.MenuView;

public interface MenuRepositoryCustom {
    
    List<MenuView> getMenuList(Long userId);

}
