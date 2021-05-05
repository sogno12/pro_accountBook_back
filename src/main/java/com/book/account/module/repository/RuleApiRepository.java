package com.book.account.module.repository;

import com.book.account.module.model.RuleApi;
import com.book.account.module.repository.custom.RuleApiRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RuleApiRepository extends JpaRepository<RuleApi, Long>, RuleApiRepositoryCustom {
 
    // TODO 결과 확인
    @Query("from RuleApi a where a.ruleId = :ruleId and a.apiId = :apiId")
    RuleApi findbyRuleIdAndApiId(@Param("ruleId") String ruleId, @Param("apiId") String apiId);
}
