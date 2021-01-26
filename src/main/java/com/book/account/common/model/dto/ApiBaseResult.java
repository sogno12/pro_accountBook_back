package com.book.account.common.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiBaseResult<T> extends CommonResult {
    private T result;
}