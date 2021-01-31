package com.book.account.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.dto.AuthenticationBean;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;

public interface AuthService {
    /**
     * 로그인
     */
    AuthenticationBean login(LoginDto loginDto, HttpServletRequest request);

    /**
     * 사용자 등록
     * @param registerDto
     */
	Long registerUser(RegisterDto registerDto);
    
}
