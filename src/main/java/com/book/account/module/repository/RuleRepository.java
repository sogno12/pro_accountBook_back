package com.book.account.module.repository;

import com.book.account.module.model.Rule;
import com.book.account.module.repository.custom.RuleRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, String>, RuleRepositoryCustom {
    
}
