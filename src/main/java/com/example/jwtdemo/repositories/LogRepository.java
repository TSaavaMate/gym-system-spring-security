package com.example.jwtdemo.repositories;

import com.example.jwtdemo.aspect.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntry,LogEntry> {
}
