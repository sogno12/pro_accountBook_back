package com.book.account.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.dto.AuthenticationBean;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.user.model.User;

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

    /**
     * 사용자 조회 (Auth)
     */
    public User loadUserByUserId(String userId);

    /**
     * 사용자 권한 조회 (Auth)
     */
    // public List<UserRoles> loadUserRole(User userDetails) {}
}
