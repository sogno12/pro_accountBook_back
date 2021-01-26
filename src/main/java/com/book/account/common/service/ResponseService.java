package com.book.account.common.service;

import com.book.account.common.model.dto.ApiBaseResult;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public <T> ApiBaseResult<T> getApiBaseResult(HttpStatus httpStatus, T data) {
        ApiBaseResult<T> result = new ApiBaseResult<>();
        result.setResult(data);
        result.setStatus(httpStatus.value());
        result.setMessage(httpStatus.getReasonPhrase());
        return result;
    }

    public <T> ApiBaseResult<T> getApiBaseResult(HttpStatus httpStatus, String messageCode, T data) {
        ApiBaseResult<T> result = new ApiBaseResult<>();
        result.setResult(data);
        result.setStatus(httpStatus.value());
        result.setMessage(httpStatus.getReasonPhrase());
        result.setMessageCode(messageCode);
        return result;
    }
    
}
