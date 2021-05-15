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
    private int sortNo;
    
   public void toUpdate(Module module) {
       this.moduleName = module.getModuleName();
       this.sortNo = module.getSortNo();
       this.chageUpdatedBy(module.getUpdatedBy());
   } 
}
