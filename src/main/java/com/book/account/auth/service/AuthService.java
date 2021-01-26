package com.book.account.auth.service;

import com.book.account.auth.model.dto.RegisterDto;

public interface AuthService {

    /**
     * 사용자 등록
     * @param registerDto
     */
	Long registerUser(RegisterDto registerDto);
    
}
