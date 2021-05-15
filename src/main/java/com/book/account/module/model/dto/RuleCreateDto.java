package com.book.account.module.model.dto;

import com.book.account.module.model.Rule;

import lombok.Data;

@Data
public class RuleCreateDto {
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private Integer sortNo;

    private Long createdBy;
    private Long updatedBy;

    public Rule toEntity() {
        return Rule.builder()
                .ruleId(this.ruleId)
                .ruleName(this.ruleName)
                .moduleId(this.moduleId)
                .sortNo(this.sortNo)
                .build();
    }

}
