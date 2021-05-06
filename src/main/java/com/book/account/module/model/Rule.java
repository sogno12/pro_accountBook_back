package com.book.account.module.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.module.model.dto.RuleUpdateDto;

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_RULE")
public class Rule extends BaseEntity {
    
    @Id
    private String ruleId;
    private String ruleName;
    private String moduleId;
    private Integer sortNo;

    public void toUpdate(RuleUpdateDto ruleUpdateDto) {
        this.ruleName = StringUtils.isEmpty(ruleUpdateDto.getRuleName()) ? this.ruleName : ruleUpdateDto.getRuleName();
        this.sortNo = StringUtils.isEmpty(ruleUpdateDto.getSortNo()) ? this.sortNo : ruleUpdateDto.getSortNo();
        this.chageUpdatedBy(ruleUpdateDto.getUpdatedBy());
    }
    
}
