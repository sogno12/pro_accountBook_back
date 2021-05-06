package com.book.account.module.service.impl;

import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.module.model.Rule;
import com.book.account.module.model.RuleApi;
import com.book.account.module.model.consts.ApiConst;
import com.book.account.module.model.dto.RuleApiCreateDto;
import com.book.account.module.model.dto.RuleApiDto;
import com.book.account.module.model.dto.RuleCreateDto;
import com.book.account.module.model.dto.RuleUpdateDto;
import com.book.account.module.repository.RuleApiRepository;
import com.book.account.module.repository.RuleRepository;
import com.book.account.module.service.RuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    RuleApiRepository ruleApiRepository;

    @Override
    public void createRule(RuleCreateDto ruleCreateDto) {
        Rule rule = ruleCreateDto.toEntity();
        rule.createdByUser(ruleCreateDto.getCreatedBy());
        ruleRepository.save(rule);
    }

    @Override
    public Rule getRule(String ruleId) {
        Rule rule = ruleRepository.findById(ruleId).orElseThrow(
            ApiConst.ResponseError.NOT_FOUND_RULE_ID::throwException
        );
        return rule;
    }

    @Override
    public void updateRule(RuleUpdateDto ruleUpdateDto) {
       // 1. 수정할 Rule 조회
       Rule rule = ruleRepository.findById(ruleUpdateDto.getRuleId())
               .orElseThrow(ApiConst.ResponseError.NOT_FOUND_RULE_ID::throwException);
        // 2. Entity 수정
        rule.toUpdate(ruleUpdateDto);
        // 3. Rule 저장
        ruleRepository.save(rule);
    }

    @Override
    public void deleteRule(String ruleId) {
        Rule rule = ruleRepository.findById(ruleId)
                .orElseThrow(ApiConst.ResponseError.NOT_FOUND_RULE_ID::throwException);
        ruleRepository.delete(rule);
    }

    @Override
    public void setRuleApis(RuleApiCreateDto ruleApis) {
        //1. RuleApi Entity
        for(String api : ruleApis.getApis()) {
            RuleApi ruleApi = ruleApis.toEntity(api);
            
            // 2. 이미 저장된 ruleApi가 있는지 찾기
            RuleApi findRuleApi = null;
            findRuleApi = ruleApiRepository.findbyRuleIdAndApiId(ruleApis.getRuleId(), api);
            
            // 3. ruleApi 없으면 저장
            if(findRuleApi == null) {
               ruleApi.createdByUser(ruleApis.getCreatedBy());
               ruleApiRepository.save(ruleApi);
           } else {
               continue;
           }
        }
        
    }

    @Override
    public RuleApiDto getApi(String ruleId, String apiId) {
        RuleApiDto apiDto = ruleApiRepository.findApibyRuleIdAndApiId(ruleId, apiId);

        if(apiDto == null) {
            throw new ApiCommonException(ApiConst.ResponseError.NOT_FOUND_API_ID.throwException());
        } else {
            return apiDto;
        }
    }

    @Override
    public void deleteRuleApi(String ruleId, String apiId) {
        RuleApi ruleApi = ruleApiRepository.findbyRuleIdAndApiId(ruleId, apiId);
        if(ruleApi == null) {
            throw new ApiCommonException(ApiConst.ResponseError.NOT_FOUND_RULE_API.throwException());
        } else {
            ruleApiRepository.delete(ruleApi);
        }
    }
    
}
