package com.book.account.menu.service.impl;

import java.util.List;

import com.book.account.menu.model.MenuView;
import com.book.account.menu.repository.MenuRepository;
import com.book.account.menu.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public List<MenuView> getMenuList(Long userId) {
        
        return menuRepository.getMenuList(userId);
    }

    
    

    

}
