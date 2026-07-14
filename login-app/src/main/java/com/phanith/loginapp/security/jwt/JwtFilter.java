package com.phanith.loginapp.security.jwt;

import com.phanith.loginapp.application.port.out.token.SaveTokenDb;
import com.phanith.loginapp.security.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("/swagger-ui") ||
                path.contains("/v3/api-docs") ||
                path.contains("webjars") ||
                path.contains("/api/v1/user/**");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            jwt = authHeader.substring(7).trim();
            if(jwt.isBlank()){
                filterChain.doFilter(request,response);
                return;
            }
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
        }
        filterChain.doFilter(request,response);



    }
}
