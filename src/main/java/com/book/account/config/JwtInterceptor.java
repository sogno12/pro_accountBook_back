package com.book.account.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.account.auth.model.consts.AuthConst.SecretType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getHeader(HEADER_AUTH);
        
        log.info(">>>> token: "+accessToken);
        if(accessToken != null && jwtTokenProvider.validateToken(SecretType.ACCESS_TOKEN, accessToken)) {
            return true;
        } else {
            // TODO exception . Unauthorized 403 처리
            throw new Exception();
        }

    }
    
}
