package com.book.account.module.repository.custom;

import com.book.account.module.model.dto.RuleDto;

public interface RuleRepositoryCustom {
 
    RuleDto getRule(String ruleId);
}
