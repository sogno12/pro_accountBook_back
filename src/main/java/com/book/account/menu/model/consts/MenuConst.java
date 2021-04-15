package com.book.account.menu.model.consts;

import com.book.account.common.model.dto.ApiCommonException;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class MenuConst {

    @Getter
    public enum ResponseError {
        DUPLICATE_MENU_ID(HttpStatus.CONFLICT, "409-1", "중복된 메뉴 ID입니다."),
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

    public enum MenuType {


    }

}