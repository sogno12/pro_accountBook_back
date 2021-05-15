package com.book.account.module.model.dto;

import com.book.account.module.model.Api;
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

    public Api getUpdatedEntity() {
        Api api = Api.builder()
                    .apiId(this.apiId)
                    .httpType(this.httpType)
                    .url(this.url)
                    .description(this.description)
                    .accessScope(this.accessScope)
                    .build();
        api.chageUpdatedBy(this.updatedBy);
        return api;
    }
}
