package com.book.account.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.common.service.ResponseService;
import com.book.account.config.JwtTokenProvider;
import com.book.account.menu.model.dto.MenuDto;
import com.book.account.my.service.MyService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/up/my")
public class MyController {
    
     private final ResponseService responseService;
    private final MyService myService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/menu")
    public ApiBaseResult<List<MenuDto>> getMyMenu(HttpServletRequest request){
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        List<MenuDto> myMenus = myService.getMyMenu(requestId);
        return responseService.getApiBaseResult(HttpStatus.OK, null);
    }
}
