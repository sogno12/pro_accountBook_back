package com.book.account.module.repository;

import com.book.account.module.model.Rule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, String> {
    
}
