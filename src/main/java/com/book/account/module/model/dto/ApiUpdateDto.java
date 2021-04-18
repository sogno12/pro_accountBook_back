package com.book.account.module.model.dto;

import com.book.account.module.model.consts.ApiConst.AccessScope;
import com.book.account.module.model.consts.ApiConst.HttpType;

import lombok.Data;

@Data
public class ApiUpdateDto {
    private String apiId;
    private HttpType httpType;
    private String url;
    private String description;
    private AccessScope accessScope;

    private Long updatedBy;
}
