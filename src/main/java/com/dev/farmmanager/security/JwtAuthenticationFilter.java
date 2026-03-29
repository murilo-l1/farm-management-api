package com.dev.farmmanager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHandler jwtHandler;
    private final CustomUserDetailsHandler userDetailsHandler;

    public JwtAuthenticationFilter(JwtHandler jwtHandler, CustomUserDetailsHandler userDetailsHandler) {
        this.jwtHandler = jwtHandler;
        this.userDetailsHandler = userDetailsHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwt = extractJwtFromCookie(request);

        String email = null;
        if (StringUtils.isNotBlank(jwt)) {
            email = jwtHandler.extractEmail(jwt);
        }

        if (StringUtils.isNotBlank(email) && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = userDetailsHandler.loadUserByUsername(email);

            if (userDetails instanceof CustomUserDetails customUserDetails) {
                final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        customUserDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwtFromCookie(@NonNull final HttpServletRequest request){
        final Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (final Cookie cookie : cookies){
                if ("jwt".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
