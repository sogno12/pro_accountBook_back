package com.book.account.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.book.account.auth.model.UserAuth;
import com.book.account.common.model.BaseEntity;
import com.book.account.mybook.model.Mybook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UP_USER")
@SequenceGenerator(name = "UP_USER_GEN", sequenceName = "UP_USER_SEQ", initialValue = 1, allocationSize = 1)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_USER_GEN")
    private Long userId;

    private String userName;
    private String email;

    // @OneToOne(mappedBy = "user")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuth userAuth;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookId")
    private Mybook mybook;


    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
        userAuth.setUser(this);
    }

    public void setMybook(Mybook mybook) {
        this.mybook = mybook;
    }
}
