package com.book.account.module.model.dto;

import lombok.Data;

@Data
public class RuleDto {
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private int sortNo;
}
