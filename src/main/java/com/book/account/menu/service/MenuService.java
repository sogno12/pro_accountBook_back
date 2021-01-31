package com.book.account.menu.service;

import java.util.List;

import com.book.account.menu.model.MenuView;

public interface MenuService {

    /**
     * 메뉴 가져오기
     */
	List<MenuView> getMenuList(Long userId);
    
}
