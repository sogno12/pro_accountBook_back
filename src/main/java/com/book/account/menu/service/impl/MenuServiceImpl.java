package com.book.account.menu.service.impl;

import java.util.List;

import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.menu.model.Menu;
import com.book.account.menu.model.consts.MenuConst;
import com.book.account.menu.model.dto.MenuCreateDto;
import com.book.account.menu.model.dto.MenuDto;
import com.book.account.menu.model.dto.MenuUpdateDto;
import com.book.account.menu.repository.MenuRepository;
import com.book.account.menu.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    /** 모든 메뉴 조회 */
    @Override
    public List<MenuDto> getMenus() {
        List<MenuDto> menus = menuRepository.getMenus();
        return menus;
    }

    /** 메뉴 상세 조회 */
    @Override
    public MenuDto getMenu(String menuId) {
        MenuDto findMenu = menuRepository.getMenu(menuId);

        return findMenu;
    }

    /** 단일 메뉴 조회 */
    public Menu getMenuById(String menuId) {
        Menu findMenu = menuRepository.findById(menuId)
                .orElseThrow(MenuConst.ResponseError.NOT_FOUND_MENU_ID::throwException);
        return findMenu;
    }

    /** 메뉴 생성 */
    @Override
    @Transactional
    public void createMenu(MenuCreateDto menuCreateDto) {
        // 1. 동일 메뉴ID 확인 -> 있으면 에러 반환
        boolean exist = menuRepository.existsById(menuCreateDto.getMenuId());

        if(exist) {
            // 2_1. 있으면 에러 반환
            throw new ApiCommonException(MenuConst.ResponseError.DUPLICATE_MENU_ID.throwException());
        } else {
            // 2_2. 동일 메뉴 ID 없으면 생성
            Menu menu = menuCreateDto.toMenuEntity();
            menuRepository.save(menu);
        }
    }

    /** 메뉴 수정 */
    @Override
    @Transactional
    public void updateMenu(MenuUpdateDto menuUpdateDto) {
        // 1. 메뉴 조회
        Menu findMenu = getMenuById(menuUpdateDto.getMenuId());
        
        // 2. 메뉴 정보 업데이트
        Menu updatedMenu = menuUpdateDto.getUpdatedEntity();
        findMenu.toUpdate(updatedMenu);
    }

    /** 메뉴 삭제 */
    @Override
    @Transactional
    public void deleteMenu(String menuId) {
        // 1. 메뉴 조회 및 삭제
        Menu findMenu = getMenuById(menuId);
        menuRepository.delete(findMenu);
    }

}
