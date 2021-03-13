package com.book.account.module.model;

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
@Table(name = "UP_MODULE")
@SequenceGenerator(name = "UP_MODULE_GEN", sequenceName = "UP_MODULE_SEQ", initialValue = 1, allocationSize = 1)
public class Module extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_MODULE_GEN")
    private Long moduleId;
    private String moduleName;
    
}
