package com.book.account.module.service;

import com.book.account.module.model.Rule;
import com.book.account.module.model.dto.RuleApiCreateDto;
import com.book.account.module.model.dto.RuleApiDto;
import com.book.account.module.model.dto.RuleCreateDto;
import com.book.account.module.model.dto.RuleUpdateDto;

public interface RuleService {

    /** 권한 생성 */
    void createRule(RuleCreateDto ruleCreateDto);

    /** 권한 상세 조회 */
    Rule getRule(String ruleId);

    /** 권한 수정 */
    void updateRule(RuleUpdateDto ruleUpdateDto);

    /** 권한 삭제 */
    void deleteRule(String ruleId);

    /** 권한-API 생성 */
    void setRuleApis(RuleApiCreateDto ruleApis);

    /** API 조회 */
    RuleApiDto getApi(String ruleId, String apiId);

    /** 권한-API 삭제 */
    void deleteRuleApi(String ruleId, String apiId);
    
}
