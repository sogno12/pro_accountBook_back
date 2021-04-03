package com.book.account.config;

import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.common.model.dto.ApiErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisor {

    private final JwtTokenProvider jwtTokenProvider;

    public ExceptionAdvisor(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 예외 처리 (ApiCommonException)
    @ExceptionHandler(ApiCommonException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ApiCommonException apiCommonException){
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(apiCommonException);
        return new ResponseEntity<>(apiErrorResponse, apiCommonException.getHttpStatus());
    }

}

