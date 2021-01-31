package com.book.account.menu.repository;

import com.book.account.menu.model.MenuBook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuBookRepository extends JpaRepository<MenuBook, Long> {
    
}
