package com.fh.fh.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecuritySchemes({
        @SecurityScheme(name = "basic Authentication", type = SecuritySchemeType.HTTP, scheme = "basic", in = SecuritySchemeIn.HEADER),
        @SecurityScheme(name = "bearer Authentication", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt", in = SecuritySchemeIn.HEADER)
})
public class SwaggerConfig {
}
