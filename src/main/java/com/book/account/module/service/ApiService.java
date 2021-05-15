package com.book.account.module.service;

import com.book.account.module.model.Api;
import com.book.account.module.model.dto.ApiCreateDto;
import com.book.account.module.model.dto.ApiDto;
import com.book.account.module.model.dto.ApiUpdateDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApiService {

    /** 모든 API 조회 */
    Page<ApiDto> getApis(ApiDto apiDto, Pageable pageable);

    /** 단일 API 조회 */
    ApiDto getApi(String apiId);

    /** API 생성 */
    void createApi(ApiCreateDto apiCreateDto);

    /** API 수정 */
    void updateApi(ApiUpdateDto apiUpdateDto);

    /** API 삭제 */
    void deleteApi(String apiId);
    
}
