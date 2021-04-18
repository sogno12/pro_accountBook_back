package com.book.account.module.repository;

import com.book.account.module.model.Api;
import com.book.account.module.repository.custom.ApiRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends JpaRepository<Api, String>, ApiRepositoryCustom {

}
