package com.book.account.menu.model.dto;

import com.book.account.common.model.BaseCreatedEntity;
import com.book.account.common.model.BaseEntity;
import com.book.account.menu.model.Menu;

import lombok.Data;

@Data
public class MenuCreateDto extends BaseEntity {

    private String menuId;
    private String menuName;
    private String upMenuId;
    private Integer sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;

    public Menu toMenuEntity() {
        Menu menu = Menu.builder().menuId(this.menuId).menuName(this.menuName).upMenuId(this.upMenuId).sortNo(this.sortNo)
                .menuPath(this.menuPath).menuIcon(this.menuIcon).description(this.description).menuType(this.menuType)
                .build();
        menu.setCreatedBy(this.getCreatedBy());
        menu.setUpdatedBy(this.getUpdatedBy());
        return menu;
    }
}
