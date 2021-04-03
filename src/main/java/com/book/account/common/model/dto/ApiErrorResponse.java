package com.book.account.common.model.dto;

import lombok.Getter;

@Getter
public class ApiErrorResponse {
    private int status;
    private String message;
    private String messageCode;
    private String messageDetail;

    public ApiErrorResponse(ApiCommonException apiCommonException) {
        this.status = apiCommonException.getHttpStatus().value();
        this.message = apiCommonException.getHttpStatus().getReasonPhrase();
        this.messageCode = apiCommonException.getMessageCode();
        this.messageDetail = apiCommonException.getMessageDetail();
    }
}
