package com.book.account.module.model.dto;

import com.book.account.module.model.RuleApi;

import lombok.Data;

@Data
public class RuleApiCreateDto {
    
    private String ruleId;
    private String[] apis;

    private Long createdBy;
    private Long updatedBy;

    public RuleApi toEntity(String api) {
        return RuleApi.builder()
                    .ruleId(this.ruleId)
                    .apiId(api)
                    .build();
    }
}
