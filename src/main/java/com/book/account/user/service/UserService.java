package com.book.account.user.service;

import com.book.account.user.model.dto.UserCreateDto;
import com.book.account.user.model.dto.UserDto;
import com.book.account.user.model.dto.UserUpdateDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    /** 모든 사용자 조회 */
    Page<UserDto> getUsers(UserDto userDto, Pageable pageable);

    /** 단일 사용자 조회 */
    UserDto getUser(Long userId);

    /** 사용자 수정 */
    void updateUser(UserUpdateDto userUpdateDto);

    /** 사용자 삭제 */
    void deleteUser(Long userId);

    /** 사용자 생성 */
    void createUser(UserCreateDto userCreateDto);

}
