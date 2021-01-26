package com.book.account.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // cors
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // setAntMatchers(http);

        http.cors().and().httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable(); // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                // .addFilterBefore(
                //         new JwtAuthenticationFilter(jwtTokenProvider, userTokenRepository, applicationSettingService),
                //         UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증

        // 필터 전에 넣는다
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    protected void setAntMatchers(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().access("@authorizationChecker.check(request, authentication)");
    }
}
