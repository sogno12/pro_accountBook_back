package com.book.account.module.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.mapper.ResponseMapper;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.config.JwtTokenProvider;
import com.book.account.module.model.Api;
import com.book.account.module.model.dto.ApiCreateDto;
import com.book.account.module.model.dto.ApiDto;
import com.book.account.module.model.dto.ApiUpdateDto;
import com.book.account.module.service.ApiService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/up/api")
public class ApiController {
    
    private final ApiService apiService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ApiBaseResult<Page<ApiDto>> getApis(ApiDto apiDto, Pageable pageable) {
        Page<ApiDto> apis = apiService.getApis(apiDto, pageable);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, apis);
    }

    @GetMapping("/{apiId}")
    public ApiBaseResult<Api> getApi(@PathVariable("apiId") String apiId) {
        Api api = apiService.getApi(apiId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, api);
    }

    @PostMapping
    public ApiBaseResult<String> createApi(HttpServletRequest request, @RequestBody ApiCreateDto apiCreateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        apiCreateDto.setCreatedBy(requestId);
        apiService.createApi(apiCreateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @PutMapping("/{apiId}")
    public ApiBaseResult<String> updateApi(HttpServletRequest request, @PathVariable("apiId") String apiId,
            @RequestBody ApiUpdateDto apiUpdateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        apiUpdateDto.setUpdatedBy(requestId);
        apiService.updateApi(apiUpdateDto);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }

    @DeleteMapping("/{apiId}")
    public ApiBaseResult<String> deleteApi(@PathVariable("apiId") String apiId) {
        apiService.deleteApi(apiId);
        return ResponseMapper.getApiBaseResult(HttpStatus.OK, "");
    }
}
