package com.book.account.menu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;

import lombok.Getter;

@Entity
@Getter
@Table(name = "UP_MENU")
public class Menu extends BaseEntity {
    
    @Id
    private String menuId;
    private String menuName;
    private String upMenuId;
    private Integer sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;

}
