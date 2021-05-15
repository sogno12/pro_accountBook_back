package com.book.account.module.service;

import java.util.List;

import com.book.account.module.model.ModuleTree;
import com.book.account.module.model.dto.ModuleCreateDto;
import com.book.account.module.model.dto.ModuleDto;
import com.book.account.module.model.dto.ModuleUpdateDto;

public interface ModuleService {

    /** 모든 모듈 트리(view) 조회 */
    List<ModuleTree> getModuleTree();

    /** 모듈 생성 */
    void createModule(ModuleCreateDto moduleCreateDto);

    /** 모듈 상세 조회 */
    ModuleDto getModule(String moduleId);

    /** 모듈 수정 */
    void updateRule(ModuleUpdateDto moduleUpdateDto);

    /** 모듈 삭제 */
    void deleteModule(String moduleId);
    
}
