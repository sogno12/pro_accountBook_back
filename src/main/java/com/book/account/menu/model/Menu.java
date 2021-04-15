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
    private Integer sortNo;
    private String menuPath;
    private String menuIcon;
    private String description;
    private String menuType;

    public void toUpdateMenu(MenuUpdateDto menuUpdateDto) {
        this.menuId = menuUpdateDto.getMenuId();
        this.menuName = StringUtils.isEmpty(menuUpdateDto.getMenuName()) ? this.menuName : menuUpdateDto.getMenuName();
        this.upMenuId = StringUtils.isEmpty(menuUpdateDto.getUpMenuId()) ? this.upMenuId : menuUpdateDto.getUpMenuId();
        this.sortNo = StringUtils.isEmpty(menuUpdateDto.getSortNo()) ? this.sortNo : menuUpdateDto.getSortNo();
        this.menuPath = menuUpdateDto.getMenuPath();
        this.menuIcon = menuUpdateDto.getMenuIcon();
        this.description = menuUpdateDto.getDescription();
        this.menuType = StringUtils.isEmpty(menuUpdateDto.getMenuType()) ? this.menuType : menuUpdateDto.getMenuType();
    }
}
