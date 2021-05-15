package com.book.account.module.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.dto.ModuleUpdateDto;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_MODULE")
public class Module extends BaseEntity {

    @Id
    private String moduleId;
    private String moduleName;
    private Integer sortNo;
    
    public void toUpdate(ModuleUpdateDto moduleUpdateDto) {
        this.moduleName = StringUtils.isEmpty(moduleUpdateDto.getModuleName()) ? this.moduleName : moduleUpdateDto.getModuleName();
        this.sortNo = StringUtils.isEmpty(moduleUpdateDto.getSortNo()) ? this.sortNo : moduleUpdateDto.getSortNo();
        this.chageUpdatedBy(moduleUpdateDto.getUpdatedBy());
    }   
}
