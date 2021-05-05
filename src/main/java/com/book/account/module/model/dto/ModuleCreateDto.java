package com.book.account.module.model.dto;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.Module;

import lombok.Data;

@Data
public class ModuleCreateDto extends BaseEntity {
    
    private String ModuleId;
    private String ModuleName;
    private Integer sortNo;

    public Module toEntity() {
        return Module.builder().moduleId(this.ModuleId).moduleName(this.ModuleName).sortNo(this.sortNo).build();
    }
}
