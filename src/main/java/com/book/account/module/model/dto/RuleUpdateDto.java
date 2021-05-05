package com.book.account.module.model.dto;

import lombok.Data;

@Data
public class RuleUpdateDto {
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private Integer sortNo;

    private Long updatedBy;
}
