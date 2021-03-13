package com.book.account.menu.model;

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
@Table(name = "UP_MENU_ROLE")
@SequenceGenerator(name = "UP_MENU_ROLE_GEN", sequenceName = "UP_MENU_ROLE_SEQ", initialValue = 1, allocationSize = 1)
public class MenuRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_MENU_ROLE_GEN")
    private Long menuRoleId;
    private String menuId;
    private Long roleId;
    
}
