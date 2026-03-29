package com.dev.farmmanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HeadersFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, String.valueOf(true));

        filterChain.doFilter(request, response);
    }

}
