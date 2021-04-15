package com.book.account.menu.service.impl;

import java.util.List;

import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.menu.model.Menu;
import com.book.account.menu.model.consts.MenuConst;
import com.book.account.menu.model.dto.MenuCreateDto;
import com.book.account.menu.model.dto.MenuUpdateDto;
import com.book.account.menu.repository.MenuRepository;
import com.book.account.menu.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    /** 모든 메뉴 조회 */
    @Override
    public List<Menu> getMenus() {
        List<Menu> findMenus = menuRepository.findAll();
        return findMenus;
    }

    /** 메뉴 상세 조회 */
    @Override
    public Menu getMenu(String menuId) {
        // TODO Exception처리
        Menu findMenu = menuRepository.findById(menuId).orElseThrow();
        return findMenu;
    }

    /** 메뉴 생성 */
    @Override
    public void createMenu(MenuCreateDto menuCreateDto) {
        // 1. 동일 메뉴ID 확인 -> 있으면 에러 반환
        Boolean isMenu = menuRepository.findById(menuCreateDto.getMenuId()).isPresent();

        if(!isMenu) {
            // 2_1. 동일 메뉴 ID 없으면 생성
            Menu menu = menuCreateDto.toMenuEntity();
            menuRepository.save(menu);
        } else {
            // 2_2. 있으면 에러 반환
            throw new ApiCommonException(MenuConst.ResponseError.DUPLICATE_MENU_ID.throwException());

        }
    }

    /** 메뉴 수정 */
    @Override
    public void updateMenu(MenuUpdateDto menuUpdateDto) {
        // 1. 메뉴 조회
        // TODO Exception처리
        Menu findMenu = menuRepository.findById(menuUpdateDto.getMenuId()).orElseThrow();
        
        // 2. 메뉴 정보 업데이트
        findMenu.toUpdateMenu(menuUpdateDto);
        
        // 3. 저장
        menuRepository.save(findMenu);
    }

    /** 메뉴 삭제 */
    @Override
    public void deleteMenu(String menuId) {
        // 1. 메뉴 조회 및 삭제
        // TODO Exception처리
        Menu findMenu = menuRepository.findById(menuId).orElseThrow();
        menuRepository.delete(findMenu);
    }

}
