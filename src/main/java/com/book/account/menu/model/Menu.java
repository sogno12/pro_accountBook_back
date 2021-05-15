package com.book.account.menu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.menu.model.dto.MenuUpdateDto;

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
@Table(name = "UP_MENU")
public class Menu extends BaseEntity {
    
    @Id
    private String menuId;
    private String menuName;
    private String upMenuId;
    private int sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;

    public void toUpdate(Menu update) {
        this.menuName = update.getMenuName();
        this.upMenuId = update.getUpMenuId();
        this.sortNo = update.getSortNo();
        this.menuPath = update.getMenuPath();
        this.menuIcon = update.getMenuIcon();
        this.description = update.getDescription();
        this.menuType = update.getMenuType();

        this.chageUpdatedBy(update.getUpdatedBy());
    }
}
