package com.book.account.module.repository.custom;

import com.book.account.module.model.dto.ModuleDto;

public interface ModuleRepositoryCustom {
 
    ModuleDto getModule(String moduleId);
}
