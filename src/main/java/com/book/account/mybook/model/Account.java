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
@Table(name = "UP_ACCOUNT")
@SequenceGenerator(name = "UP_ACCOUNT_GEN", sequenceName = "UP_ACCOUNT_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_ACCOUNT_GEN")
    private Long accountId;
    private String accountName;
    private String description;
    private Long initMoney;
    private Long bookId;
    private Long upAccountId;
    
}
