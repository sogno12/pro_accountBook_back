package com.book.account.module.model.dto;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.RuleApi;

import lombok.Data;

@Data
public class RuleApiCreateDto extends BaseEntity {
    
    private String ruleId;
    private String[] apis;

    public RuleApi toEntity(String api) {
        return RuleApi.builder().ruleId(this.ruleId).apiId(api).build();
    }
}
