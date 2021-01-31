package com.book.account.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.dto.AuthenticationBean;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.auth.service.AuthService;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.common.service.ResponseService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/auth")
public class AuthController {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping(value = "/login")
    public ApiBaseResult<AuthenticationBean> login(HttpServletRequest request, @RequestBody LoginDto loginDto) {

        // JWT 로그인 인증
        AuthenticationBean authenticationBean = authService.login(loginDto, request);
        return responseService.getApiBaseResult(HttpStatus.OK, authenticationBean);
    }
    
    @PostMapping(value = "/register")
    public ApiBaseResult<String> register(HttpServletRequest request, @RequestBody RegisterDto registerDto) {
        Long userId = authService.registerUser(registerDto);

        return responseService.getApiBaseResult(HttpStatus.OK, "");
    }

}
