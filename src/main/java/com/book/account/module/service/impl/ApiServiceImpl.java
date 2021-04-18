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

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public Page<ApiDto> getApis(ApiDto apiDto, Pageable pageable) {
        Page<ApiDto> apis = apiRepository.getApis(apiDto, pageable);
        return apis;
    }

    @Override
    public Api getApi(String apiId) {
        Api api = apiRepository.findById(apiId).orElseThrow(ApiConst.ResponseError.NOT_FOUND_API_ID::throwException);
        return api;
    }

    @Override
    public void createApi(ApiCreateDto apiCreateDto) {
        Api api = apiCreateDto.toApiEntity();
        api.setCreatedBy(apiCreateDto.getCreatedBy());
        api.setUpdatedBy(apiCreateDto.getUpdatedBy());
        apiRepository.save(api);
    }

    @Override
    public void updateApi(ApiUpdateDto apiUpdateDto) {
        // 1. API ENTITY
        Api api = apiRepository.findById(apiUpdateDto.getApiId())
        .orElseThrow(ApiConst.ResponseError.NOT_FOUND_API_ID::throwException);
        api.toUpdateEntity(apiUpdateDto);
        // 2. 저장
        apiRepository.save(api);
    }

    @Override
    public void deleteApi(String apiId) {
        Api api = apiRepository.findById(apiId)
                .orElseThrow(ApiConst.ResponseError.NOT_FOUND_API_ID::throwException);
        apiRepository.delete(api);
    }

}
