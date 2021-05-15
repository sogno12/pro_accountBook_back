package com.book.account.menu.service;

import java.util.List;

import com.book.account.menu.model.dto.MenuCreateDto;
import com.book.account.menu.model.dto.MenuDto;
import com.book.account.menu.model.dto.MenuUpdateDto;

public interface MenuService {

    /**
     * 모든 메뉴 조회
     */
    List<MenuDto> getMenus();

    /**
     * 메뉴 상세 조회
     * @param menuId
     * @return
     */
    MenuDto getMenu(String menuId);

    /**
     * 메뉴 생성
     * @param menuCreateDto
     */
    void createMenu(MenuCreateDto menuCreateDto);

    /**
     * 메뉴 수정
     * @param menuUpdateDto
     */
    void updateMenu(MenuUpdateDto menuUpdateDto);

    /**
     * 메뉴 삭제
     * @param menuId
     */
    void deleteMenu(String menuId);
    
}
