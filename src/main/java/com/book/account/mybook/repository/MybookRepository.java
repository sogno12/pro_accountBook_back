package com.book.account.mybook.repository;

import com.book.account.mybook.model.Mybook;
import com.book.account.mybook.repository.custom.MybookRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MybookRepository extends JpaRepository<Mybook, Long>, MybookRepositoryCustom {
    
}
