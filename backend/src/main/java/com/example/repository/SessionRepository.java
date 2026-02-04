package com.example.repository;

import com.example.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserId(Long userId);
    List<Session> findByStatus(String status);
    List<Session> findByChannelId(Long channelId);
}
