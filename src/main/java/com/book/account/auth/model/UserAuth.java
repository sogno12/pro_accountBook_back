package com.book.account.auth.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.book.account.common.model.BaseEntity;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst.Status;
import com.book.account.user.model.dto.UserUpdateDto;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UP_USER_AUTH")
public class UserAuth extends BaseEntity {

    @Id
    private String loginId;
    private String loginPwd;

    @ColumnDefault("ACTIVE")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    // 연관관계설정 - 연관엔티티의 PK를 가지고 있는 쪽이 주인
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void chageStatus(Status status, Long updatedId) {
        this.status = status;
        this.chageUpdatedBy(updatedId);
    }
}