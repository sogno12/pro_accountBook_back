package com.book.account.my.service.impl;

import java.util.List;

import com.book.account.menu.model.dto.MenuDto;
import com.book.account.my.service.MyService;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    public List<MenuDto> getMyMenu(Long requestId) {
        // TODO 내 권한 조회
        // TODO 권한에 맞는 메뉴 조회

        
        return null;
    }
    
}
