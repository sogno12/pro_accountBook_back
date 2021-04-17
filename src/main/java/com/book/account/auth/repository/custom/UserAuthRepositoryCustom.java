package com.book.account.auth.repository.custom;

import com.book.account.auth.model.UserAuth;

public interface UserAuthRepositoryCustom {

    UserAuth findByUserId(Long userId);
    
}
