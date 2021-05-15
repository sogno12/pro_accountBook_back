package com.book.account.menu.repository.custom;

import java.util.List;

import com.book.account.menu.model.dto.MenuDto;

public interface MenuRepositoryCustom {

    List<MenuDto> getMenus();
    MenuDto getMenu(String menuId);
}
