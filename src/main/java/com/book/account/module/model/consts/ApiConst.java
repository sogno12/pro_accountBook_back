package com.book.account.module.model.consts;

import com.book.account.common.model.dto.ApiCommonException;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ApiConst {

  @Getter
  public enum ResponseError {
    NOT_FOUND_API_ID(HttpStatus.NOT_FOUND, "404-1", "존재하지 않는 API_ID"),;

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

  public enum AccessScope {
    AUTHORIZED // 인증 + 접근권한
    , AUTHENTICATED// 인증받은 사용자
    , PUBLIC // 모든 사용자
  }

  public enum HttpType {
    GET, POST, PUT, DELETE
  }
}
