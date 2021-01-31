package com.book.account.mybook.service.impl;


import com.book.account.common.model.dto.ApiCommonException;
import com.book.account.menu.model.MenuBook;
import com.book.account.menu.model.consts.MenuConst.MenuType;
import com.book.account.menu.repository.MenuBookRepository;
import com.book.account.mybook.model.Mybook;
import com.book.account.mybook.model.dto.MybookCreateDto;
import com.book.account.mybook.repository.MybookRepository;
import com.book.account.mybook.service.MybookService;
import com.book.account.user.model.User;
import com.book.account.user.model.consts.UserConst;
import com.book.account.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybookServiceImpl implements MybookService {

    @Autowired
    MybookRepository mybookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuBookRepository menuBookRepository;

    @Override
    public void newMybook(Long userId) {
        // 1. 가계부 갯수 확인 (1인당 3개까지)
        Long bookCount = mybookRepository.countByIdAndIsUsed(userId);

        // 2. 가계부 갯수가 2개 이하면 생성
        if(bookCount < 3) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiCommonException(UserConst.ResponseError.UNAUTHORIZED_NOT_FOUND_ID.throwException()));

            MybookCreateDto createDto = new MybookCreateDto(user.getUserId(), user.getUserName());

            Mybook newMybook = createDto.toMybookEntity();
            newMybook.setCreatedBy(userId);
            newMybook.setUpdatedBy(userId);
            
            Long bookId = mybookRepository.save(newMybook).getBookId();

            MenuBook newMenuBook = new MenuBook(MenuType.MyAccount, bookId, userId);
            menuBookRepository.save(newMenuBook);
        }
    }



    
}
