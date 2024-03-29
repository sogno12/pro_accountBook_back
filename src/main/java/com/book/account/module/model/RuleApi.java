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
@Table(name = "UP_RULE_API")
@SequenceGenerator(name = "UP_RULE_API_GEN", sequenceName = "UP_RULE_API_SEQ", initialValue = 1, allocationSize = 1)
public class RuleApi extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_RULE_API_GEN")
    private Long ruleApiId;
    private String ruleId;
    private String apiId;
}
