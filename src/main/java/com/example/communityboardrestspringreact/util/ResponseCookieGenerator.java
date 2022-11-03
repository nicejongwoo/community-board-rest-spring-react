package com.example.communityboardrestspringreact.util;

import org.springframework.http.ResponseCookie;

public class ResponseCookieGenerator {

    public static ResponseCookie responseCookie(String key, String value, boolean httpOnly, boolean secure, String path, long maxAge) {
        return ResponseCookie.from(key, value)
                .sameSite("None")
                .httpOnly(httpOnly)
                .secure(secure)
                .path(path)
                .maxAge(maxAge)
                .build();
    }

}
