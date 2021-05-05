package com.book.account.module.model.dto;

import lombok.Data;

@Data
public class ModuleUpdateDto {
    
    private String ModuleId;
    private String ModuleName;
    private Integer sortNo;

    private Long updatedBy;
}
