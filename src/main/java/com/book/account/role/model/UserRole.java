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
@Table(name = "UP_USER_ROLE")
@SequenceGenerator(name = "UP_USER_ROLE_GEN", sequenceName = "UP_USER_ROLE_SEQ", initialValue = 1, allocationSize = 1)
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_USER_ROLE_GEN")
    private Long userRoleId;
    private Long roleId;
    private Long userId;
}
