package com.book.account.auth.service.impl;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.auth.repository.UserAuthRepository;
import com.book.account.auth.service.AuthService;
import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.mybook.model.Mybook;
import com.book.account.mybook.service.MybookService;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst;
import com.book.account.user.repository.UserRepository;
import com.book.account.util.CommonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    MybookService mybookservice;

    // 사용자 등록
    @Override
    public Long registerUser(RegisterDto registerDto) {

        // 1. 비밀번호 암호화
        registerDto.setLoginPwd(passwordEncoder.encode(CommonUtils.encBySha256(registerDto.getLoginPwd())));

        // 2. 중복체크
        if (isExistLoginId(registerDto.getLoginId())) {
            throw UserConst.ResponseError.DUPLICATE_USER_ID.throwException();
        }

        // 3. Entity 변환
        User user = registerDto.toUserEntity();
        UserAuth userAuth = registerDto.toUserAuthEntity();

        // 4. 사용자 등록
        user.setCreatedBy(0L);
        user.setUpdatedBy(0L);
        userAuth.setCreatedBy(0L);
        userAuth.setUpdatedBy(0L);

        user.setUserAuth(userAuth);
        userRepository.save(user);

        Long userId = user.getUserId();


        // 5. 사용자 최초 가계부 생성
        mybookservice.newMybook(userId);

        return userId;

    }

    private boolean isExistLoginId(String loginId) {
        return userAuthRepository.findById(loginId).isPresent();
    }

    // 로그인
    @Override
    public boolean login(LoginDto loginDto) {

        // 1. 계정 정보 조회
        UserAuth loginUserAuth = checkPassword(loginDto.getLoginId(), loginDto.getLoginPwd());
        
        if (loginUserAuth != null) {
            User user = loginUserAuth.getUser();

            // TODO 토큰? 로그인 정보 저장방법? 고민

            return true;
        } else {
            throw new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException());
        }

    }

    public UserAuth checkPassword(String loginId, String loginPwd){
        
        // 1. ID로 계정찾기
        UserAuth userAuth = userAuthRepository.findById(loginId)
                    .orElseThrow(() -> new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException()));

        // 2. 패스워드 일치 확인
        if( passwordEncoder.matches(loginPwd, userAuth.getLoginPwd())) {
            // TODO

        } else {
            throw new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException());
        }

        return userAuth;
    }
    
}
