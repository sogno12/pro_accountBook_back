package com.book.account.module.model.dto;

import com.book.account.module.model.Rule;

import lombok.Data;

@Data
public class RuleUpdateDto {
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private int sortNo;

    private Long updatedBy;

    public Rule getUpdatedEntity() {
        Rule rule = Rule.builder()
                        .ruleId(this.ruleId)
                        .ruleName(this.ruleName)
                        .moduleId(this.moduleId)
                        .sortNo(this.sortNo)
                        .build();
        rule.chageUpdatedBy(this.updatedBy);
        return rule;
    }
}
