package com.book.account.module.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.consts.ApiConst.AccessScope;
import com.book.account.module.model.consts.ApiConst.HttpType;
import com.book.account.module.model.dto.ApiUpdateDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_API")
public class Api extends BaseEntity {

    @Id
    private String apiId;

    @Enumerated(value = EnumType.STRING)
    private HttpType httpType;
    private String url;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private AccessScope accessScope;

    public void toUpdateEntity(ApiUpdateDto apiUpdateDto) {
        this.apiId = apiUpdateDto.getApiId();
        this.httpType = apiUpdateDto.getHttpType();
        this.url = apiUpdateDto.getUrl();
        this.description = apiUpdateDto.getDescription();
        this.accessScope = apiUpdateDto.getAccessScope();
        this.chageUpdatedBy(apiUpdateDto.getUpdatedBy());
    }
}
