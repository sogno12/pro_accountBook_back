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
@Table(name = "UP_CATEGORY")
@SequenceGenerator(name = "UP_CATEGORY_GEN", sequenceName = "UP_CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_CATEGORY_GEN")
    private Long categoryId;
    private String categoryName;
    private Long upCategoryId;

}
