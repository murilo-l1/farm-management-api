package com.dev.farmmanager.config;

import com.dev.farmmanager.security.JwtAuthenticationFilter;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${farm.cors.origins}")
    private String allowedOrigins;

    private static final String[] WHITELIST_PATTERNS = {
            "/api/auth/**",
            "/api/farm/user/me"
            // endpoints de documentation swagger
    };

    private static final String FARM_API_PATTERN = "/api/farm/**";

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(configure -> configure.configurationSource(this.corsConfigurationSource())).
                headers(h -> h.contentSecurityPolicy(policy -> policy.policyDirectives("script-src 'self'"))
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                        .httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).preload(true).maxAgeInSeconds(31536000))
                        .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                         .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers(WHITELIST_PATTERNS).permitAll()
                        .requestMatchers(FARM_API_PATTERN).hasRole("USER")
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .anyRequest().hasRole("ADMIN")
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", createCorsConfig(allowedOrigins));

        return source;
    }

    private CorsConfiguration createCorsConfig(String allowedOrigins) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues(); // applies basic CORS config
        configuration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL)); // allow all methods
        configuration.setAllowedOrigins(null); // remove all origins allowed entry
        configuration.validateAllowCredentials();

        final List<String> originsConfig = List.of(allowedOrigins.split(","));
        for (final String originAllowed : originsConfig) {
            configuration.addAllowedOrigin(originAllowed.trim());
        }
        return configuration;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(15_000_000L));
        factory.setMaxRequestSize(DataSize.ofBytes(15_000_000L));
        return factory.createMultipartConfig();
    }
}