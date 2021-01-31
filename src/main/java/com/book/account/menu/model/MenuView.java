package com.book.account.menu.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class MenuView {
    
    @Id
    private String menuId;
    private Long bookId;
    private String menuName;
    private String upMenuId;
    private Integer sortNo;
    private String menuPath;
    private String menuIcon;
    private String menuType;
    private Long userId; 
}
