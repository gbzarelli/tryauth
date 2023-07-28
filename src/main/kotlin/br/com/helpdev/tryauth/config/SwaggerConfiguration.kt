package br.com.helpdev.tryauth.config

import io.swagger.v3.oas.models.Components

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


private const val JWT = "JWT"
private const val BEARER = "bearer"

@Configuration
class SwaggerConfiguration {

    @Bean
    fun openAPI(): OpenAPI? {
        return OpenAPI().addSecurityItem(SecurityRequirement().addList("Bearer Authentication"))
            .components(Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())).info(
                Info().title("TryAuth REST API")
                    .description("Authorization and authentication sample with resource server (keycloak)")
                    .version("1.0").contact(
                        Contact().name("Guilherme Biff Zarelli").email("gbzarelli@helpdev.com.br")
                            .url("https://www.helpdev.com.br")
                    ).license(
                        License().name("License of API").url("API license URL")
                    )
            )
    }

    private fun createAPIKeyScheme(): SecurityScheme? {
        return SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat(JWT).scheme(BEARER)
    }
}