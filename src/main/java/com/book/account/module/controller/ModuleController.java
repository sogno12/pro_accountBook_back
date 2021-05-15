package com.book.account.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.mapper.ResponseMapper;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.config.JwtTokenProvider;
import com.book.account.module.model.Module;
import com.book.account.module.model.ModuleTree;
import com.book.account.module.model.dto.ModuleCreateDto;
import com.book.account.module.model.dto.ModuleUpdateDto;
import com.book.account.module.service.ModuleService;

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
@RequestMapping(value = "/api/up/module")
public class ModuleController {
    
    private final ModuleService moduleService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/tree")
    public ApiBaseResult<List<ModuleTree>> getModuleTree() {
        List<ModuleTree> moduleTree = moduleService.getModuleTree();
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, moduleTree);
    }
    
    @GetMapping("/{moduleId}")
    public ApiBaseResult<Module> getModule(@PathVariable("moduleId") String moduleId) {
        Module module = moduleService.getModule(moduleId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, module);
    }

    @PostMapping
    public ApiBaseResult<String> createModule(HttpServletRequest request, @RequestBody ModuleCreateDto moduleCreateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        moduleCreateDto.setCreatedBy(requestId);
        moduleService.createModule(moduleCreateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @PutMapping("/{moduleId}")
    public ApiBaseResult<String> updateRule(HttpServletRequest request, @RequestBody ModuleUpdateDto moduleUpdateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        moduleUpdateDto.setUpdatedBy(requestId);
        moduleService.updateRule(moduleUpdateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @DeleteMapping("/{moduleId}")
    public ApiBaseResult<String> deleteModule(@PathVariable("moduleId") String moduleId) {
        moduleService.deleteModule(moduleId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }
}
