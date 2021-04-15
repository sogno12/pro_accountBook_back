package com.book.account.user.model.consts;

import com.book.account.common.model.dto.ApiCommonException;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class UserConst {
    
    @Getter
    public enum ResponseError {
        DUPLICATE_USER_ID(HttpStatus.CONFLICT, "409-1", "중복된 USER_ID"),
        UNAUTHORIZED_NOT_FOUND_ID(HttpStatus.UNAUTHORIZED,"401-1","Unauthorized"),
        INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "401-2", "유효하지 않은 토큰"),
        ;

        HttpStatus status;
        String messageCode;
        String messageDetail;

        ResponseError(HttpStatus status, String messageCode, String messageDetail) {
            this.status = status;
            this.messageCode = messageCode;
            this.messageDetail = messageDetail;
        }

        public ApiCommonException throwException() {
            return new ApiCommonException(this.status, this.messageCode, this.messageDetail);
        }

    }

    public enum AuthType {
        ADMIN, USER;
    }
}
