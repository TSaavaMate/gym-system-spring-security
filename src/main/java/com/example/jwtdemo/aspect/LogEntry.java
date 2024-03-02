package com.example.jwtdemo.aspect;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Logs")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    private String loggerName;
    private String logLevel;
    private String threadName;
    private String className;
    private String methodName;
    private String message;
    private String exceptionStackTrace;
    private Integer userId;
}
