package com.book.account.module.service.impl;

import java.util.List;

import com.book.account.module.model.Module;
import com.book.account.module.model.ModuleTree;
import com.book.account.module.model.consts.ApiConst;
import com.book.account.module.model.dto.ModuleCreateDto;
import com.book.account.module.model.dto.ModuleUpdateDto;
import com.book.account.module.repository.ModuleRepository;
import com.book.account.module.repository.ModuleTreeRepository;
import com.book.account.module.service.ModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleTreeRepository moduleTreeRepository;

    @Autowired
    private ModuleRepository moduleRepository;
    
    @Override
    public List<ModuleTree> getModuleTree() {
        List<ModuleTree> moduletree = moduleTreeRepository.findAll();
        return moduletree;
    }

    @Override
    public void createModule(ModuleCreateDto moduleCreateDto) {
        Module module = moduleCreateDto.toEntity();
        module.createdByUser(moduleCreateDto.getCreatedBy());
        moduleRepository.save(module);
    }

    @Override
    public Module getModule(String moduleId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(
            ApiConst.ResponseError.NOT_FOUND_MODULE_ID::throwException
        );
        return module;
    }

    @Override
    public void updateRule(ModuleUpdateDto moduleUpdateDto) {
       // 1. 수정할 Module 조회
        Module module = moduleRepository.findById(moduleUpdateDto.getModuleId())
                .orElseThrow(ApiConst.ResponseError.NOT_FOUND_MODULE_ID::throwException);
        // 2. Entity 수정
        module.toUpdate(moduleUpdateDto);
        // 3. Module 저장
        moduleRepository.save(module);
    }

    @Override
    public void deleteModule(String moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(ApiConst.ResponseError.NOT_FOUND_MODULE_ID::throwException);
        moduleRepository.delete(module);
    }

    
}
