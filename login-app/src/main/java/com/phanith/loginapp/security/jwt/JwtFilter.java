package com.phanith.loginapp.security.jwt;

import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.security.CookieUtil;
import com.phanith.loginapp.security.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final SaveTokenDb saveTokenDb;
    private final CustomUserDetailService  customUserDetailService;

    public JwtFilter(JwtService jwtService, @Lazy SaveTokenDb saveTokenDb, CustomUserDetailService customUserDetailService) {
        this.jwtService = jwtService;
        this.saveTokenDb = saveTokenDb;
        this.customUserDetailService = customUserDetailService;
    }
    private String resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(CookieUtil.ACCESS_COOKIE.equals(cookie.getName()) && !cookie.getValue().isEmpty()) {
                    return cookie.getValue();
                }
            }
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();
            if (!token.isBlank()){
                return token;
            }
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("/swagger-ui") ||
                path.contains("/v3/api-docs") ||
                path.contains("webjars") ||
                path.equals("/api/v1/user/login") ||
                path.equals("/api/v1/user/register") ||
                path.equals("/api/v1/user/refresh");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt=resolveToken(request);
        final String username;
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try{

            username = jwtService.extractUsername(jwt);
            if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                boolean isJwtValid = jwtService.isTokenValid(jwt,userDetails);
                boolean isTokenValid = saveTokenDb.isTokenValid(jwt);
                if(isJwtValid && isTokenValid){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or Expired token");
            return;
        }
        filterChain.doFilter(request,response);



    }
}
