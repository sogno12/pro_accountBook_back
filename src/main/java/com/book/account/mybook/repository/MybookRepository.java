package com.book.account.mybook.repository;

import com.book.account.mybook.model.Mybook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MybookRepository extends JpaRepository<Mybook, Long> {
    
}
