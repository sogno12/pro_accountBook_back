package com.book.account.common.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    private int status;

    private String message;

    private String messageCode;

}
