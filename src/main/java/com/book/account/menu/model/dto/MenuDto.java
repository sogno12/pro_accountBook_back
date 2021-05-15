package com.book.account.menu.model.dto;

import lombok.Data;

@Data
public class MenuDto {
    private String menuId;
    private String menuName;
    private String upMenuId;
    private int sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;
}
