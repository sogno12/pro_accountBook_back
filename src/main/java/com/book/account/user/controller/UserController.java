package com.book.account.user.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.account.auth.model.consts.AuthConst.SecretType;
import com.book.account.common.model.dto.ApiBaseResult;
import com.book.account.common.service.ResponseService;
import com.book.account.config.JwtTokenProvider;
import com.book.account.user.model.dto.UserCreateDto;
import com.book.account.user.model.dto.UserDto;
import com.book.account.user.model.dto.UserUpdateDto;
import com.book.account.user.service.UserService;

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

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/up/users")
public class UserController {

    private final ResponseService responseService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ApiBaseResult<Page<UserDto>> getUsers(UserDto userDto, Pageable pageable) {
        Page<UserDto> users = userService.getUsers(userDto, pageable);

        return responseService.getApiBaseResult(HttpStatus.OK, users);
    }

    @GetMapping("/{userId}")
    public ApiBaseResult<UserDto> getUser(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getUser(userId);
        return responseService.getApiBaseResult(HttpStatus.OK, userDto);
    }

    @PostMapping
    public ApiBaseResult<String> createUser(HttpServletRequest request, @RequestBody UserCreateDto userCreateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        userCreateDto.setCreatedBy(requestId);
        userCreateDto.setUpdatedBy(requestId);
        userService.createUser(userCreateDto);
        return responseService.getApiBaseResult(HttpStatus.OK, "");

    }

    @PutMapping("/{userId}")
    public ApiBaseResult<String> updateUser(HttpServletRequest request, @PathVariable("userId") Long userId,
            @RequestBody UserUpdateDto userUpdateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        userUpdateDto.setUpdatedBy(requestId);
        userService.updateUser(userUpdateDto);
        return responseService.getApiBaseResult(HttpStatus.OK, "");
    }

    @DeleteMapping("/{userId}")
    public ApiBaseResult<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return responseService.getApiBaseResult(HttpStatus.OK, "");
    }

    @PutMapping("/status")
    public ApiBaseResult<String> updateUserStatus(HttpServletRequest request,
            @RequestBody UserUpdateDto userUpdateDto) {
        Long requestId = jwtTokenProvider.getUserId(request, SecretType.ACCESS_TOKEN);
        userUpdateDto.setUpdatedBy(requestId);
        userService.updateUser(userUpdateDto);
        return responseService.getApiBaseResult(HttpStatus.OK, "");
    }
}
