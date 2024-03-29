package com.book.account.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.auth.model.dto.AuthenticationBean;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.auth.service.AuthService;
import com.book.account.common.mapper.ResponseMapper;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.config.JwtTokenProvider;
import com.book.account.user.model.consts.UserConst;
import com.book.account.util.CommonUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ApiBaseResult<AuthenticationBean> login(HttpServletRequest request, @RequestBody LoginDto loginDto) {

        // 1. IP 주소
        String ip = CommonUtils.getClientIp(request);
        AuthenticationBean authenticationBean = authService.login(loginDto, ip);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, authenticationBean);
    }
    
    @PostMapping("/register")
    public ApiBaseResult<String> register(HttpServletRequest request, @RequestBody RegisterDto registerDto) {
        authService.registerUser(registerDto);

        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @GetMapping("/checkAuthorization")
    public ApiBaseResult<String> checkAuthrization(@RequestHeader(value = "Authorization") String token) {
        boolean valid = jwtTokenProvider.validateToken(SecretType.ACCESS_TOKEN, jwtTokenProvider.getTokenWithoutPrefix(token));

        if (!jwtTokenProvider.validateToken(SecretType.ACCESS_TOKEN, jwtTokenProvider.getTokenWithoutPrefix(token))) {
            throw new ApiCommonException(UserConst.ResponseError.INVALID_TOKEN.throwException());
        } else {
            return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
        }
    }

}
