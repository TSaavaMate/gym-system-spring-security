package com.example.jwtdemo.auth;

import com.example.jwtdemo.aspect.Loggable;
import com.example.jwtdemo.config.JwtTokenService;
import com.example.jwtdemo.entities.LoginAttempt;
import com.example.jwtdemo.entities.Token;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.exceptions.BlockedUserException;
import com.example.jwtdemo.exceptions.InvalidCredentialException;
import com.example.jwtdemo.models.requests.authRequest.AuthenticationRequest;
import com.example.jwtdemo.models.requests.authRequest.ChangePasswordRequest;
import com.example.jwtdemo.models.requests.registrationRequest.UserRegistrationRequest;
import com.example.jwtdemo.models.responses.AuthenticationResponse;
import com.example.jwtdemo.repositories.LoginAttemptRepository;
import com.example.jwtdemo.repositories.TokenRepository;
import com.example.jwtdemo.repositories.UserRepository;
import com.example.jwtdemo.utils.Role;
import com.example.jwtdemo.utils.TokenType;
import com.example.jwtdemo.utils.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private static final Integer MAX_LOGIN_ATTEMPTS = 3;
    private static final Integer MIN_LOGIN_ATTEMPTS = 1;
    private final CredentialConfigurer credentialConfigurer;

    private final UserRepository userRepository;

    private final LoginAttemptRepository loginAttemptRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    private final AuthenticationManager authManager;

    private final TokenRepository tokenRepository;

    @Transactional
    public AuthenticationResponse register(UserRegistrationRequest request) {

        String username = credentialConfigurer.generateUniqueUsername(request.getFirstname(), request.getLastname());
        String encoded = passwordEncoder.encode(request.getPassword());

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .username(username)
                .password(encoded)
                .role(Role.ROLE_USER)
                .isActive(request.getIsActive())
                .status(UserStatus.ACTIVE)
                .build();

        var savedUser = userRepository.save(user);

        var jwt = jwtTokenService.generateToken(user);

        saveUserToken(savedUser, jwt);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {


            try {
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(InvalidCredentialException::new);

            if (user.getStatus().equals(UserStatus.BLOCKED)){
                throw new BlockedUserException();
            }

            if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
                throw new AuthenticationException();
            }

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var jwt = jwtTokenService.generateToken(user);

            revokeAllTokens(user);
            saveUserToken(user,jwt);

            return AuthenticationResponse.builder()
                    .token(jwt)
                    .build();
        } catch (AuthenticationException e) {
            handleFailedLoginAttempt(request.getEmail());
            throw e;
        }
    }

    @Loggable
    private void handleFailedLoginAttempt(String email) {
        var optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) return; //email was wrong no need to increment login attempt

        var user = optionalUser.get();
        var userId = user.getId();

        var userAttempts = loginAttemptRepository.findByUserId(userId);


        if (userAttempts.isEmpty()){
            var attempt = LoginAttempt.builder()
                    .userId(userId)
                    .lastLogin(LocalDateTime.now())
                    .loginAttempts(MIN_LOGIN_ATTEMPTS)
                    .build();
            loginAttemptRepository.save(attempt);
            return;
        }

        var attemptCount = userAttempts.get().getLoginAttempts();

        userAttempts.get().setLastLogin(LocalDateTime.now());
        userAttempts.get().setLoginAttempts(++attemptCount);


        loginAttemptRepository.save(userAttempts.get());



        if (attemptCount >= MAX_LOGIN_ATTEMPTS){

            user.setStatus(UserStatus.BLOCKED);

            userRepository.save(user);
        }

    }


    @Transactional
    public AuthenticationResponse changePasswordAndAuthenticate(ChangePasswordRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String oldPassword = request.getOldPassword();

        if (!passwordEncoder.matches(oldPassword,user.getPassword())){
            throw new InvalidCredentialException("Wrong email or password provided");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        var jwt=jwtTokenService.generateToken(user);

        revokeAllTokens(user);
        saveUserToken(user,jwt);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }

    private void saveUserToken(User savedUser, String jwt) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwt)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }
    private void revokeAllTokens(User user){
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

}
