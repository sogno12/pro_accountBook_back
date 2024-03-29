package com.book.account.user.service.impl;

import com.book.account.auth.model.UserAuth;
import com.book.account.auth.repository.UserAuthRepository;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst;
import com.book.account.user.model.dto.UserCreateDto;
import com.book.account.user.model.dto.UserDto;
import com.book.account.user.model.dto.UserUpdateDto;
import com.book.account.user.repository.UserRepository;
import com.book.account.user.service.UserService;
import com.book.account.util.EncUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public Page<UserDto> getUsers(UserDto userDto, Pageable pageable) {

        Page<UserDto> users = userRepository.getUsers(userDto, pageable);
        return users;
    }

    @Override
    public UserDto getUser(Long userId) {
        UserDto user = userRepository.getUser(userId);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        // TODO. USER_ROLE

        // 2. USER Entity
        User user = getUserById(userUpdateDto.getUserId());
        
        // 2. USER UPDATE
               User updatedUser = userUpdateDto.getUpdatedEntity();

        user.toUpdateEntity(userUpdateDto);

        // 3. USER_AUTH Entity
        UserAuth userAuth = userAuthRepository.findByUserId(userUpdateDto.getUserId());
        userAuth.chageStatus(userUpdateDto.getStatus(), userUpdateDto.getUpdatedBy());

        // 4. 저장
        userRepository.save(user);
        userAuthRepository.save(userAuth);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        // TODO. USER_ROLE

        // USER_AUTH
        userAuthRepository.deleteByUser(getUserById(userId));
        // USER
        userRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void createUser(UserCreateDto userCreateDto) {
        // 1. LoginId 중복체크
        if (isExistLoginId(userCreateDto.getLoginId())) {
            throw UserConst.ResponseError.DUPLICATE_USER_ID.throwException();
        }

        // 2. 비밀번호 생성
        userCreateDto.setLoginPwd(passwordEncoder.encode(EncUtils.encBySha256(getPolicyPassword())));

        // 3. Entity 변환
        User user = userCreateDto.toUserEntity();
        UserAuth userAuth = userCreateDto.toUserAuthEntity();

        // 4. 사용자 등록
        user.createdByUser(userCreateDto.getCreatedBy());
        userAuth.createdByUser(userCreateDto.getCreatedBy());

        user.setUserAuth(userAuth);
        userRepository.save(user);

        // TODO 5. 비밀번호 이메일로 안내

    }

    /** 정책에 따른 패스워드 반환 */
    private String getPolicyPassword() {
        // TODO 패스워드 정책
        return "1234";
    }

    /** UserId 로 USER 조회 */
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserConst.ResponseError.NOT_FOUND_USER_ID::throwException);
        return user;
    }

    /** 로그인 아이디 기존 존재유무 확인 */
    private boolean isExistLoginId(String loginId) {
        return userAuthRepository.findById(loginId).isPresent();
    }

}
