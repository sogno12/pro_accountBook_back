package com.book.account.module.repository;

import com.book.account.module.model.ModuleTree;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleTreeRepository extends JpaRepository<ModuleTree, Long> {
    
}
