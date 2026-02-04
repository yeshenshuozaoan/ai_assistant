package com.example.repository;

import com.example.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByUserId(Long userId);
    List<Log> findBySessionId(Long sessionId);
    List<Log> findByAction(String action);
}
