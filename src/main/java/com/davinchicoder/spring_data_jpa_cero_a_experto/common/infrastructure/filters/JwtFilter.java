package com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.filters;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@AllArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("No token found");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);
        log.info("Token: {}", token);

        String username = jwtService.getUsername(token);
        log.info("Username: {}", username);

        boolean tokenExpired = jwtService.isTokenExpired(token);
        boolean canBeTokenRenewed = jwtService.canBeTokenRenewed(token);

        if (tokenExpired && !canBeTokenRenewed) {
            log.error("Token expired");
            filterChain.doFilter(request, response);
            return;
        }

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            log.error("Invalid user");
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(username)
                .password("password")
                .roles("USER")
                .build();


        if (tokenExpired && canBeTokenRenewed) {
            String renewToken = jwtService.renewToken(token, userDetails);
            response.setHeader("Authorization", "Bearer " + renewToken);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response); // pasa al sgte?
    }
}
