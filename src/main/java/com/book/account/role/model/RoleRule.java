package com.book.account.role.model;

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
@Table(name = "UP_ROLE_RULE")
@SequenceGenerator(name = "UP_ROLE_RULE_GEN", sequenceName = "UP_ROLE_RULE_SEQ", initialValue = 1, allocationSize = 1)
public class RoleRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_ROLE_RULE_GEN")
    private Long roleRuleId;
    private Long roleId;
    private String ruleId;

}