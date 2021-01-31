package com.book.account.menu.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.book.account.menu.model.consts.MenuConst.MenuType;

import lombok.Getter;

@Entity
@Getter
@Table(name = "UP_MENU_BOOK")
@SequenceGenerator(name = "UP_MENU_BOOK_GEN", sequenceName = "UP_MENU_BOOK_SEQ", initialValue = 1, allocationSize = 1)
public class MenuBook {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_MENU_BOOK_GEN")
    private Long menuBookId;

    @Enumerated(value = EnumType.STRING)
    private MenuType upMenuId;
    private Long bookId;
    private Long userId;

    public MenuBook (MenuType upMenuId, Long bookId, Long userId){
        this.upMenuId = upMenuId;
        this.bookId = bookId;
        this.userId = userId;
    }
    
}
