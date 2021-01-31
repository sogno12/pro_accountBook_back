package com.book.account.mybook.model.dto;

import com.book.account.mybook.model.Mybook;

import lombok.Data;

@Data
public class MybookCreateDto {

    private String bookName;
    private Boolean isUsed;
    private Long userId;

    public MybookCreateDto(Long userId, String userName) {
        this.userId = userId;
        this.bookName = userName + "님의 새 가계부";
    }

    public Mybook toMybookEntity() {
         return Mybook.builder().bookName(this.bookName).userId(this.userId).isUsed(Boolean.TRUE).build();
    }
    
}
