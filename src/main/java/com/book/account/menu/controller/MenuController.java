package com.book.account.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.mapper.ResponseMapper;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.config.JwtTokenProvider;
import com.book.account.menu.model.Menu;
import com.book.account.menu.model.dto.MenuCreateDto;
import com.book.account.menu.model.dto.MenuDto;
import com.book.account.menu.model.dto.MenuUpdateDto;
import com.book.account.menu.service.MenuService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/up/menu")
public class MenuController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MenuService menuService;

    @GetMapping
    public ApiBaseResult<List<MenuDto>> getMenus(){
        List<MenuDto> menus = menuService.getMenus();
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, menus);
    }

    @GetMapping("/{menuId}")
    public ApiBaseResult<MenuDto> getMenu(@PathVariable("menuId") String menuId) {
        MenuDto menu = menuService.getMenu(menuId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, menu);
    }

    @PostMapping
    public ApiBaseResult<String> createMenu(HttpServletRequest request, @RequestBody MenuCreateDto menuCreateDto){
        Long userId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        menuCreateDto.setCreatedBy(userId);    
        
        menuService.createMenu(menuCreateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @PutMapping("/{menuId}")
    public ApiBaseResult<String> updateMenu(HttpServletRequest request, @RequestBody MenuUpdateDto menuUpdateDto) {
        Long userId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        menuUpdateDto.setUpdatedBy(userId);

        menuService.updateMenu(menuUpdateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @DeleteMapping("/{menuId}")
    public ApiBaseResult<String> deleteMenu(@PathVariable("menuId") String menuId){
        menuService.deleteMenu(menuId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }


}
