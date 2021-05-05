package com.book.account.module.model.dto;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.Rule;

import lombok.Data;

@Data
public class RuleCreateDto extends BaseEntity {
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private Integer sortNo;

    public Rule toEntity() {
        return Rule.builder().ruleId(this.ruleId).ruleName(this.ruleName).moduleId(this.moduleId).sortNo(this.sortNo)
                .build();
    }

}
