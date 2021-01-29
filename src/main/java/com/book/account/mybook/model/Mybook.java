package com.book.account.mybook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
public class Mybook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_MY_BOOK_GEN")
    private Long bookId;
    private String bookName;
    private Boolean isUsed;
    private Long userId;

    
}
