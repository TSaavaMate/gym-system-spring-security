package com.example.jwtdemo.config;

import com.example.jwtdemo.exceptions.JwtParsingException;
import com.example.jwtdemo.repositories.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.jwtdemo.utils.JwtExtractor.extractJwt;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenService jwtTokenService;

    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String jwt;
        try{
           jwt = extractJwt(request);
        }catch (JwtParsingException ex){
            filterChain.doFilter(request,response);
            return;
        }

        String username= jwtTokenService.extractUserName(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            boolean isTokenValidInService = jwtTokenService.isTokenValid(jwt, userDetails);
            var isTokenValidInDb = tokenRepository.findByToken(jwt)
                    .map(t->!t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if (isTokenValidInService && isTokenValidInDb){
                var authToken = new UsernamePasswordAuthenticationToken(
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
        filterChain.doFilter(request,response);
    }
}
