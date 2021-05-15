package com.book.account.module.model.dto;

import com.book.account.module.model.Module;

import lombok.Data;

@Data
public class ModuleUpdateDto {
    
    private String ModuleId;
    private String ModuleName;
    private int sortNo;

    private Long updatedBy;

    public Module getUpdatedEntity() {
        Module module = Module.builder()
                                .moduleId(this.ModuleId)
                                .moduleName(this.ModuleName)
                                .sortNo(this.sortNo)
                                .build();
        module.chageUpdatedBy(this.updatedBy);
        return module;
    }
}
