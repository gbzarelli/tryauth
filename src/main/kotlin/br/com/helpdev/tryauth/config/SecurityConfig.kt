package br.com.helpdev.tryauth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { auth ->
            run {
                auth.requestMatchers(
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/actuator/**",
                    "/metrics/**",
                    "/error/**"
                )
                    .permitAll()
                    .requestMatchers("/api/**")
                    .authenticated()
            }
        }.oauth2ResourceServer { oauth2ResourceServer ->
            run {
                oauth2ResourceServer.jwt(Customizer.withDefaults())
            }
        }.build()
    }
}

