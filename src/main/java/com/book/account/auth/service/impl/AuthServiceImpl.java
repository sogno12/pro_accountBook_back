package com.book.account.auth.service.impl;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.auth.repository.UserAuthRepository;
import com.book.account.auth.service.AuthService;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst;
import com.book.account.user.repository.UserRepository;
import com.book.account.util.CommonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

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

        // 4. 등록
        user.setCreatedBy(0L);
        userAuth.setUpdatedBy(0L);

        user.setUserAuth(userAuth);

        userRepository.save(user);

        Long userId = user.getUserId();

        return userId;

    }

    private boolean isExistLoginId(String loginId) {
        return userAuthRepository.findById(loginId).isPresent();
    }
    
}
