package com.book.account.auth.service;

import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;

public interface AuthService {
    /**
     * 로그인
     */
    boolean login(LoginDto loginDto);

    /**
     * 사용자 등록
     * @param registerDto
     */
	Long registerUser(RegisterDto registerDto);
    
}
