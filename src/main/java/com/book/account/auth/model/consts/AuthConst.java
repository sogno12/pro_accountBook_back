package com.book.account.auth.model.consts;

public class AuthConst {
    
    public enum SecretType {
         ACCESS_TOKEN    //액세스 토큰
        ,REFRESH_TOKEN  //리프레시 토큰
    }
    

    public enum AccessScope {
          AUTHORIZED // 인증 + 접근권한
        , AUTHENTICATED// 인증받은 사용자
        , PUBLIC // 모든 사용자
    }    
}
