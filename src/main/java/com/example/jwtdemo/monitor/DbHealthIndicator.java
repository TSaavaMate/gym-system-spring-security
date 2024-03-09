package com.example.jwtdemo.monitor;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbHealthIndicator implements HealthIndicator {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Health health() {
        return isDbHealthy() ?
                Health.up().withDetail("db service","healthy").build() :
                Health.down().withDetail("db service","not healthy").build();
    }
    private boolean isDbHealthy(){
        var result = jdbcTemplate.queryForObject("select 1", Integer.class);

        if (result == null) return false;

        return result==1;
    }
}
