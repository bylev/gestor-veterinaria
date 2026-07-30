package com.veterinaria.gestion_mascotas.web.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Veterinaria API", version = "1.0"),
        tags = {
                @Tag(name = "01 - Auth", description = "Manage veterinarian authentication"),
                @Tag(name = "Appointment", description = "Manage appointments in the veterinary clinic"),
                @Tag(name = "Owner", description = "Manage pet owners in the veterinary clinic"),
                @Tag(name = "Pet", description = "Manage pets in the veterinary clinic"),
                @Tag(name = "Vet", description = "Manage veterinarians in the clinic")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
