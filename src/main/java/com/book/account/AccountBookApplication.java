package com.book.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
public class AccountBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountBookApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// 1_3. Auditing을 사용하기 위해 Application 클래스에 @EnableJpaAuditing 어노테이션을 추가해준다.
}
