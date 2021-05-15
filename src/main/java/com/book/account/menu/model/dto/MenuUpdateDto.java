package com.book.account.menu.model.dto;

import com.book.account.menu.model.Menu;

import lombok.Data;

@Data
public class MenuUpdateDto {
    private String menuId;
    private String menuName;
    private String upMenuId;
    private int sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;

    private Long updatedBy;

    public Menu getUpdatedEntity() {
        Menu menu = Menu.builder()
                        .menuId(this.menuId)
                        .menuName(this.menuName)
                        .upMenuId(this.upMenuId)
                        .sortNo(this.sortNo)
                        .menuPath(this.menuPath)
                        .menuIcon(this.menuIcon)
                        .description(this.description)
                        .menuType(this.menuType)
                        .build();
        menu.chageUpdatedBy(this.updatedBy);
        return menu;
    }
}
