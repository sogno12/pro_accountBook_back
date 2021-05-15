package com.book.account.module.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.mapper.ResponseMapper;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.config.JwtTokenProvider;
import com.book.account.module.model.dto.RuleApiCreateDto;
import com.book.account.module.model.dto.RuleApiDto;
import com.book.account.module.model.dto.RuleCreateDto;
import com.book.account.module.model.dto.RuleDto;
import com.book.account.module.model.dto.RuleUpdateDto;
import com.book.account.module.service.RuleService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/rule")
public class RuleController {

    private final RuleService ruleService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{ruleId}")
    public ApiBaseResult<RuleDto> getRule(@PathVariable("ruleId") String ruleId) {
        RuleDto rule = ruleService.getRule(ruleId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, rule);
    }

    @PostMapping
    public ApiBaseResult<String> createRule(HttpServletRequest request, @RequestBody RuleCreateDto ruleCreateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        ruleCreateDto.setCreatedBy(requestId);
        ruleService.createRule(ruleCreateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @PutMapping("/{ruleId}")
    public ApiBaseResult<String> updateRule(HttpServletRequest request, @RequestBody RuleUpdateDto ruleUpdateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        ruleUpdateDto.setUpdatedBy(requestId);
        ruleService.updateRule(ruleUpdateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @DeleteMapping("/{ruleId}")
    public ApiBaseResult<String> deleteRule(@PathVariable("ruleId") String ruleId) {
        ruleService.deleteRule(ruleId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @PostMapping("/api")
    public ApiBaseResult<String> createRuleApi(HttpServletRequest request, @RequestBody RuleApiCreateDto ruleApis) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        ruleApis.setCreatedBy(requestId);
        ruleService.setRuleApis(ruleApis);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @GetMapping("/{ruleId}/{apiId}")
    public ApiBaseResult<RuleApiDto> getApi(@PathVariable("ruleId") String ruleId, @PathVariable("apiId") String apiId){
        RuleApiDto apiDto = ruleService.getApi(ruleId, apiId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, apiDto);
    }
    
    @DeleteMapping("/{ruleId}/{apiId}")
    public ApiBaseResult<String> deleteRuleApi(@PathVariable("ruleId") String ruleId,
            @PathVariable("apiId") String apiId) {
                ruleService.deleteRuleApi(ruleId, apiId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, ""); 
    }  
}
