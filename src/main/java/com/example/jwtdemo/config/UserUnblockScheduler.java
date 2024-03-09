package com.example.jwtdemo.config;

import com.example.jwtdemo.aspect.Loggable;
import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.repositories.LoginAttemptRepository;
import com.example.jwtdemo.repositories.UserRepository;
import com.example.jwtdemo.utils.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserUnblockScheduler {
    private static final Integer unblockOffset = 5;
    private final LoginAttemptRepository loginAttemptRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedDelay = 300000)
    @Loggable
    public void unblockUsers(){
        System.out.println("inside unblocking");
        List<User> blockedUsers = userRepository.findUsersByStatus(UserStatus.BLOCKED);

        blockedUsers.forEach(user ->{
            var loginAttempt = loginAttemptRepository.findByUserId(user.getId()).orElseThrow();

            if (loginAttempt.getLastLogin().isBefore(LocalDateTime.now().minusMinutes(unblockOffset))){
                user.setStatus(UserStatus.ACTIVE);
                loginAttempt.setLoginAttempts(0);

                loginAttemptRepository.save(loginAttempt);
                userRepository.save(user);
            }
        });
    }
}
