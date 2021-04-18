package com.book.account.module.repository.custom;

import com.book.account.module.model.dto.ApiDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiRepositoryCustom {
    Page<ApiDto> getApis(ApiDto apiDto, Pageable pageable);
}
