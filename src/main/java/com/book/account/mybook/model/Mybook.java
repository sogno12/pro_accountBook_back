package com.book.account.mybook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.menu.model.consts.MenuConst.MenuType;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_MY_BOOK")
@SequenceGenerator(name = "UP_MY_BOOK_GEN", sequenceName = "UP_MY_BOOK_SEQ", initialValue = 1, allocationSize = 1)
public class Mybook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_MY_BOOK_GEN")
    private Long bookId;
    
    private String bookName;
    
    @ColumnDefault("1")
    private Boolean isUsed;
    private Long userId;

    
}
