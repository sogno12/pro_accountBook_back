package com.book.account.mybook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_BOOK")
@SequenceGenerator(name = "UP_BOOK_GEN", sequenceName = "UP_BOOK_SEQ", initialValue = 1, allocationSize = 1)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_BOOK_GEN")
    private Long bookId;
    private String bookName;
    private String description;
    private Long userId;

}
