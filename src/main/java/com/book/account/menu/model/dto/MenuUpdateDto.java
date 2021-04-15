package com.book.account.menu.model.dto;

import com.book.account.common.model.BaseEntity;

import lombok.Data;

@Data
public class MenuUpdateDto extends BaseEntity {
    private String menuId;
    private String menuName;
    private String upMenuId;
    private Integer sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;
}
