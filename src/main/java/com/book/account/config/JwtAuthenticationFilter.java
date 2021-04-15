package com.book.account.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.UserToken;
import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.auth.repository.UserTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserTokenRepository userTokenRepository;

    /**
     * Requset로 들어오는 JwtToken 유효성 검증 Filter
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    
                // 1. Token 얻기
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
    token = jwtTokenProvider.getTokenWithoutPrefix(token);
    
    // 2. 토큰 유효성 검증
    if(!StringUtils.isEmpty(token) && jwtTokenProvider.validateToken(SecretType.ACCESS_TOKEN, token)){
        Long userId = jwtTokenProvider.getUserId(SecretType.ACCESS_TOKEN, token);
        UserToken userToken = userTokenRepository.findById(userId).orElseThrow();

        // 2_1. 저장된 유저의 토큰과 일치하면
        if(token.equals(userToken.getToken())) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
        // 2_2. 일치하지 않으면
            log.debug("--> 다른 곳에서 로그인 = " + request.getRemoteAddr());
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
    

}
    
}