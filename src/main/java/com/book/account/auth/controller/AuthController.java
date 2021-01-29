package com.book.account.auth.controller;

import javax.servlet.http.HttpServletRequest;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/auth")
public class AuthController {

    private final AuthService authService;
    private final ResponseService responseService;

    @PostMapping(value = "/login")
    public ApiBaseResult<Boolean> login(HttpServletRequest request, @RequestBody LoginDto loginDto) {
        log.info(">>>> login >>" + loginDto.getLoginId());
        log.info(">>>> login >>" + loginDto.getLoginPwd());

        Boolean result = authService.login(loginDto);

        return responseService.getApiBaseResult(HttpStatus.OK, result);
    }
    
    @PostMapping(value = "/register")
    public ApiBaseResult<String> register(HttpServletRequest request, @RequestBody RegisterDto registerDto) {
        log.info(">>>> registerDto >>>> " + registerDto.getLoginId());
        log.info(">>>> registerDto >>>> " + registerDto.toString());

        Long userId = authService.registerUser(registerDto);

        return responseService.getApiBaseResult(HttpStatus.OK, "");
    }

}
