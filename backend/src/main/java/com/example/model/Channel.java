package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "channels")
public class Channel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "channel_name")
    private String channelName;
    
    @Column(name = "channel_type")
    private String channelType;
    
    private String config;
    
    private String status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getChannelName() {
        return channelName;
    }
    
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    
    public String getChannelType() {
        return channelType;
    }
    
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
    
    public String getConfig() {
        return config;
    }
    
    public void setConfig(String config) {
        this.config = config;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
