package com.book.account.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

    @Value("${accountbook.jwt.tokenType}")
    private String tokenType;

    @Value("${accountbook.jwt.secretKey}")
    private String accessTokenSecretKey;

    // 1분
    @Value("${accountbook.jwt.tokenValidTime}")
    private long accessTokenValidMilisecond; // 1시간만 토큰 유효
    // private long tokenValidMilisecond = 1000L * 60 * 60; // 1시간만 토큰 유효

    private static final String CLAIMS_USER_ID = "userId";

    /**
     * JWT 토큰 생성
     */
    public String createToken(SecretType secretType, String userPk, Long userId) {

        log.info(">>>> secretType: "+ secretType);
        log.info(">>>> userPk: "+ userPk);
        log.info(">>>> userId: "+ userId);

        Claims claims = Jwts.claims().setSubject(userPk);   // 제목
        claims.put(CLAIMS_USER_ID, userId);     // 담고싶은정보
        Date issuedAt = new Date();
        return Jwts.builder().setClaims(claims) // 데이터
                .setIssuedAt(issuedAt) // 토큰 발행일자
                .setExpiration(new Date(issuedAt.getTime() + getTokenValidMilisecondByType(secretType))) // set Expire
                                                                                                         // Time
                .signWith(SignatureAlgorithm.HS256, getSecretKeyByType(secretType)) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    /**
     * Jwt 토큰에서 로그인 아이디 구별 정보 추출
     */
    public String getUserPk(SecretType secretType, String token) {
        return Jwts.parser().setSigningKey(getSecretKeyByType(secretType)).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * JWT 토큰에서 만료기간 정보 추출
     */
    public Date getExpiresIn(SecretType secretType, String token) {
        return Jwts.parser().setSigningKey(getSecretKeyByType(secretType)).parseClaimsJws(token).getBody()
                .getExpiration();
    }

    /**
     * JWT 토큰에서 회원아이디 정보 추출
     */
    public Long getUserId(SecretType secretType, String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(getSecretKeyByType(secretType)).parseClaimsJws(token)
                .getBody().get(CLAIMS_USER_ID).toString());
    }


    // Request의 Header에서 token 파싱 : "X-AUTH-TOKEN: jwt토큰"
    public String resolveToken(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }

    public boolean validateToken(SecretType secretType, String token) {
        try {
            String secretKey = getSecretKeyByType(secretType);
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String getSecretKeyByType(SecretType secretType) {

        String secretKey = null;
        switch (secretType) {
            case ACCESS_TOKEN:
            default:
                secretKey = accessTokenSecretKey;
                break;
        }
        return secretKey;
    }

    private Long getTokenValidMilisecondByType(SecretType secretType) {

        Long tokenValidMilisecond = null;
        switch (secretType) {
            case ACCESS_TOKEN:
            default:
                tokenValidMilisecond = accessTokenValidMilisecond;
                break;
        }
        return tokenValidMilisecond;
    }

    public String getTokenWithoutPrefix(String token) {
        if (!StringUtils.isEmpty(token)) {
            String[] tokenArray = token.split(" ");
            if (tokenArray.length > 1) {
                return tokenArray[1];
            } else {
                return tokenArray[0];
            }
        } else {
            return "";
        }

    }

    public long getUserId(HttpServletRequest request, SecretType secretType) {
        String token = resolveToken(request);
        token = getTokenWithoutPrefix(token);
        return getUserId(secretType, token);
    }

    // public long getDeptId(HttpServletRequest request, SecretType secretType) {
    //     String token = resolveToken(request);
    //     token = getTokenWithoutPrefix(token);
    //     return getDeptId(secretType, token);
    // }
}