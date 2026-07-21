package com.phanith.loginapp.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public static final String ACCESS_COOKIE = "accessToken";
    public static final String REFRESH_COOKIE = "refreshToken";


    @Value("${ACCESS_EXPIRATION}")
    private long accessExpirationMs;
    @Value("${REFRESH_EXPIRATION}")
    private long refreshExpirationMs;

    @Value("${COOKIE_SECURE:false}")
    private boolean cookieSecure;

    public void addAccessTokenCookie(HttpServletResponse response, String token) {
        addCookie(response, ACCESS_COOKIE, token, accessExpirationMs / 1000);
    }

    public void addRefreshTokenCookie(HttpServletResponse response, String token) {
        // Scoped to /api/v1/user so it's only ever sent to the auth
        // endpoints (login/refresh/logout), never to /profile/** calls
        // where it isn't needed.
        addCookie(response, REFRESH_COOKIE, token, refreshExpirationMs / 1000, "/api/v1/user");
    }

    public void clearAuthCookies(HttpServletResponse response) {
        addCookie(response, ACCESS_COOKIE, "", 0);
        addCookie(response, REFRESH_COOKIE, "", 0, "/api/v1/user");
    }

    private void addCookie(HttpServletResponse response, String name, String value, long maxAgeSeconds) {
        addCookie(response, name, value, maxAgeSeconds, "/");
    }

    private void addCookie(HttpServletResponse response, String name, String value, long maxAgeSeconds, String path) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(cookieSecure)
                // Lax: sent on normal same-site requests and on top-level
                // navigation, withheld on cross-site POST/PUT/DELETE — the
                // main defense against CSRF now that auth rides on a cookie.
                .sameSite("Lax")
                .path(path)
                .maxAge(maxAgeSeconds)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }


}
