package com.book.account.module.repository;

import com.book.account.module.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, String>  {
    
}
