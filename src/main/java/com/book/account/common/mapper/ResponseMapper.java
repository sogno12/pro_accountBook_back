package com.book.account.common.mapper;

import com.book.account.common.model.dto.ApiBaseResult;

import org.springframework.http.HttpStatus;

public class ResponseMapper {

    public static <T> ApiBaseResult<T> getApiBaseResult(HttpStatus httpStatus, T data) {
        ApiBaseResult<T> result = new ApiBaseResult<>();
        result.setResult(data);
        result.setStatus(httpStatus.value());
        result.setMessage(httpStatus.getReasonPhrase());
        return result;
    }

    public static <T> ApiBaseResult<T> getApiBaseResult(HttpStatus httpStatus, String messageCode, T data) {
        ApiBaseResult<T> result = new ApiBaseResult<>();
        result.setResult(data);
        result.setStatus(httpStatus.value());
        result.setMessage(httpStatus.getReasonPhrase());
        result.setMessageCode(messageCode);
        return result;
    }
    
}
