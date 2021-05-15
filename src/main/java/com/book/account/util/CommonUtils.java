package com.book.account.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
    
    /**
     * CLIKENT IP 주소
     */
    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null)
            ip = req.getRemoteAddr();
        return ip;
    }
}
