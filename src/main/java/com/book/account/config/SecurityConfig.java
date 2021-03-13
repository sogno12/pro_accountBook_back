package com.book.account.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 사용을 위한 어노테이션 선언
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    // cors
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // setAntMatchers(http);

        httpSecurity.cors().and().httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
                .csrf().disable(); // rest api이므로 csrf 보안이 필요없으므로 disable처리.
                // .addFilterBefore(
                //         new JwtAuthenticationFilter(jwtTokenProvider, userTokenRepository, applicationSettingService),
                //         UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증

        // 필터 전에 넣는다
        httpSecurity.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    protected void setAntMatchers(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().access("@authorizationChecker.check(request, authentication)");
    }

    // 스프링 시큐리티 참고 블로그
    // https://velog.io/@eungook/WebSecurityConfigurerAdapter%EB%A1%9C-%EC%A0%81%EB%8B%B9%ED%9E%88-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
    // @Override
    // public void configure(HttpSecurity security) throws Exception {
    //     security
    //              .antMatchers("/admin/*").authenticated().and().formLogin().loginPage("/login") // 로그인이 필요한 url 정의
    //              .defaultSuccessUrl("/admin/main", true);    // 로그인 페이지와 로그인 성공 시 보내줄 url 정의
    // }
}
