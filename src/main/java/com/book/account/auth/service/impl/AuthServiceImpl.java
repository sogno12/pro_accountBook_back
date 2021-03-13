package com.book.account.auth.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.model.UserToken;
import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.auth.model.dto.AuthenticationBean;
import com.book.account.auth.model.dto.LoginDto;
import com.book.account.auth.model.dto.RegisterDto;
import com.book.account.auth.repository.UserAuthRepository;
import com.book.account.auth.repository.UserTokenRepository;
import com.book.account.auth.service.AuthService;
import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.config.JwtTokenProvider;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst;
import com.book.account.user.repository.UserRepository;
import com.book.account.util.CommonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    UserTokenRepository userTokenRepository;


    @Value("${accountbook.jwt.tokenType}")
    private String tokenType;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    /**
     * 사용자 등록
     */
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


        // TODO 5. 사용자 최초 가계부 생성

        return userId;
    }

    /**
     * loginId 기존 존재 여부 반환 (True/False)
     */
    private boolean isExistLoginId(String loginId) {
        return userAuthRepository.findById(loginId).isPresent();
    }

    /**
     * 로그인
     */
    @Override
    public AuthenticationBean login(LoginDto loginDto, HttpServletRequest request) {

        // 1. 계정 정보 조회
        UserAuth loginUserAuth = checkPassword(loginDto.getLoginId(), loginDto.getLoginPwd());
        
        if (loginUserAuth != null) {
            User user = loginUserAuth.getUser();

            // 4.TODO: IP 가져와서 AccessLog 기록하기
            String ip = CommonUtils.getClientIp(request);
            // AccessLog accessLog = new AccessLog(user, ip);
            // insertAccessLog(accessLog);

            // 5. Token 반환
            return getAuthenticationBean(loginUserAuth.getLoginId(), user, loginUserAuth, ip);

        } else {
            throw new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException());
        }

    }

    /**
     * 패스워드 일치 확인
     */
    public UserAuth checkPassword(String loginId, String loginPwd){
        
        // 1. ID로 계정찾기
        UserAuth userAuth = userAuthRepository.findById(loginId)
                    .orElseThrow(() -> new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException()));

        // 2. 패스워드 일치 확인
        if(passwordEncoder.matches(loginPwd, userAuth.getLoginPwd())) {
            // 2.1 맞으면 계정 정보 반환
            return userAuth;
        } else {
            // 2.2 틀리다면 로그인 실패 반환
            throw new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException());
        }
    }
    
    /**
     * 토큰 생성/반환 및 UserToken 저장
     */
    public AuthenticationBean getAuthenticationBean(String loginId, User user, UserAuth userAuth, String ip) {

        // 1. 토큰 생성
        String accessToken = jwtTokenProvider.createToken(SecretType.ACCESS_TOKEN, user.getUserId(), String.valueOf(loginId));

        // 2. 토큰 반환 정보는 로그인API 반환 시와 동일한 구조로 반환.
        AuthenticationBean authenticationBean = new AuthenticationBean();
        authenticationBean.setUserId(user.getUserId());
        authenticationBean.setUserName(user.getUserName());
        authenticationBean.setLoginId(loginId);
        authenticationBean.setTokenType(tokenType);
        authenticationBean.setAccessToken(accessToken);
        authenticationBean.setExpiresIn(jwtTokenProvider.getExpiresIn(SecretType.ACCESS_TOKEN, accessToken));

        // 3. UserToken 테이블에 저장
        UserToken userToken = userTokenRepository.findById(user.getUserId()).orElse(new UserToken(user.getUserId()));
        userToken.setUpdate(accessToken, jwtTokenProvider.getExpiresIn(SecretType.ACCESS_TOKEN, accessToken), ip);
        userTokenRepository.save(userToken);

        return authenticationBean;
    }


}
