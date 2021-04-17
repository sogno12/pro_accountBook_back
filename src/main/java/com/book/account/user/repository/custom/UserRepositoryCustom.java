package com.book.account.user.repository.custom;

import com.book.account.user.model.dto.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

    Page<UserDto> getUsers(UserDto userDto, Pageable pageable);
    
    UserDto getUserDetails(Long userId);
}
