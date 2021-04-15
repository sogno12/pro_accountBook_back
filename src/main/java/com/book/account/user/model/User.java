package com.book.account.user.model;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.book.account.auth.model.UserAuth;
import com.book.account.common.model.BaseEntity;
import com.book.account.role.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@DynamicInsert
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    public User(Long userId) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UP_USER_GEN")
    private Long userId;

    private String userName;
    private String email;

    // @OneToOne(mappedBy = "user")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuth userAuth;


    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
        userAuth.setUser(this);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserRole> roles = new ArrayList<>();

    public void setRoles(List<UserRole> roles){
        this.roles = roles;
    } 

    /*** ↓ USER_DETAILS ↓ ***/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simple = this.roles.stream().map(userRole -> "ROLE_"+userRole.getRoleId()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return simple;
    }

    @Override
    public String getPassword() {
        return userAuth.getLoginPwd();
    }

    @Override
    public String getUsername() {   // 로그인아이디
        return userAuth.getLoginId();
    }

    public String getRealUserName() { // 진짜 사용자명
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
