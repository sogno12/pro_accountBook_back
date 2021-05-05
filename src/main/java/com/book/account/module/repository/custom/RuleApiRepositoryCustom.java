package com.book.account.module.repository.custom;

import com.book.account.module.model.dto.RuleApiDto;

public interface RuleApiRepositoryCustom {

    RuleApiDto findApibyRuleIdAndApiId(String ruleId, String apiId);
}
