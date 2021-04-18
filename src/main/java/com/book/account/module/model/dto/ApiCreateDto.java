package com.book.account.module.model.dto;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.Api;
import com.book.account.module.model.consts.ApiConst.AccessScope;
import com.book.account.module.model.consts.ApiConst.HttpType;

import lombok.Data;

@Data
public class ApiCreateDto extends BaseEntity {
    private String apiId;
    private HttpType httpType;
    private String url;
    private String description;
    private AccessScope accessScope;

    public Api toApiEntity() {
        return Api.builder().apiId(this.apiId).httpType(this.httpType).url(this.url)
        .description(this.description).accessScope(this.accessScope).build();
    }
}
