package com.book.account.module.service.impl;

import com.book.account.module.model.Api;
import com.book.account.module.model.consts.ApiConst;
import com.book.account.module.model.dto.ApiCreateDto;
import com.book.account.module.model.dto.ApiDto;
import com.book.account.module.model.dto.ApiUpdateDto;
import com.book.account.module.repository.ApiRepository;
import com.book.account.module.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public Page<ApiDto> getApis(ApiDto apiDto, Pageable pageable) {
        Page<ApiDto> apis = apiRepository.getApis(apiDto, pageable);
        return apis;
    }

    @Override
    public ApiDto getApi(String apiId) {
        ApiDto apiDto = apiRepository.getApi(apiId);
        return apiDto;
    }

    public Api getApiById(String apiId) {
        Api api = apiRepository.findById(apiId).orElseThrow(ApiConst.ResponseError.NOT_FOUND_API_ID::throwException);
        return api;
    }

    @Override
    @Transactional
    public void createApi(ApiCreateDto apiCreateDto) {
        Api api = apiCreateDto.toApiEntity();
        api.createdByUser(apiCreateDto.getCreatedBy());
        apiRepository.save(api);
    }

    @Override
    @Transactional
    public void updateApi(ApiUpdateDto apiUpdateDto) {
        // 1. 기존 API ENTITY 찾기
        Api api = getApiById(apiUpdateDto.getApiId());
        
        // 2. 내용이 변경된 ENTITY 생성
        Api updatedApi = apiUpdateDto.getUpdatedEntity();
        
        // 3. UPDATE
        api.toUpdate(updatedApi);
    }
    
    @Override
    @Transactional
    public void deleteApi(String apiId) {
        Api api = getApiById(apiId);
        apiRepository.delete(api);
    }

}
