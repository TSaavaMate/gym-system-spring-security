package com.example.jwtdemo.config;

import com.example.jwtdemo.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import static com.example.jwtdemo.utils.JwtExtractor.extractJwt;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        var jwt = extractJwt(request);

        var storedToken = tokenRepository.findByToken(jwt);

        if (storedToken.isPresent()){
            var token = storedToken.get();

            token.setRevoked(true);
            token.setExpired(true);

            tokenRepository.save(token);
        }
    }
}
