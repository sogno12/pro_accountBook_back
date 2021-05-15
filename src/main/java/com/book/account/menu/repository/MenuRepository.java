package com.book.account.menu.repository;

import com.book.account.menu.model.Menu;
import com.book.account.menu.repository.custom.MenuRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>, MenuRepositoryCustom {
    
}
