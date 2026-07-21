package com.phanith.loginapp.infrastructure.config;

import com.phanith.loginapp.security.CookieUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    final String cookieScheme = "accessTokenCookie";
    final String refreshCookie = "refreshTokenCookie";
    final String bearerSchemeName = "bearerAuth";
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("login system API")
                        .version("1.0")
                        .description("API documentation for login system"))
        .components(new Components()
                .addSecuritySchemes(cookieScheme,new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.COOKIE)
                        .name(CookieUtil.ACCESS_COOKIE))
                .addSecuritySchemes(refreshCookie,new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.COOKIE)
                        .name(CookieUtil.REFRESH_COOKIE)
                )
                .addSecuritySchemes(bearerSchemeName,new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .name(bearerSchemeName)))
        .addSecurityItem(new SecurityRequirement()
                .addList(cookieScheme)
                .addList(bearerSchemeName));


    }
}
