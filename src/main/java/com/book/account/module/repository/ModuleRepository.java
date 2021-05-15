package com.book.account.module.repository;

import com.book.account.module.model.Module;
import com.book.account.module.repository.custom.ModuleRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, String>, ModuleRepositoryCustom {

}
