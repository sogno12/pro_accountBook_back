package com.book.account.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.common.service.ResponseService;
import com.book.account.config.JwtTokenProvider;
import com.book.account.menu.model.MenuView;
import com.book.account.menu.service.MenuService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/menu")
public class MenuController {

    private final ResponseService responseService;
    private final MenuService menuService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ApiBaseResult<List<MenuView>> getMenu(HttpServletRequest request) {

        log.info(">>>> getMenu: ");

        Long userId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);

        log.info(">>>>userId: " + userId);
        List<MenuView> results = menuService.getMenuList(userId);


        return responseService.getApiBaseResult(HttpStatus.OK, results);
    }

}
