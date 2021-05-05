package com.book.account.my.service;

import java.util.List;

import com.book.account.menu.model.dto.MenuDto;

public interface MyService {

    /** 내 메뉴 조회 */
    List<MenuDto> getMyMenu(Long requestId);
    
}
