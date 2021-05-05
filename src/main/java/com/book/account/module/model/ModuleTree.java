package com.book.account.module.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "UP_MODULE_TREE")
public class ModuleTree {
    
    @Id
    private Long no;
    private String upModuleId;
    private String moduleId;
    private String moduleName;
    private Long lv; 
}
