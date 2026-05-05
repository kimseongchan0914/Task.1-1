package com.gsm._8th.class4.backed.task._1._1.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseIdxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false, insertable = false, unique = true)
    protected Long idx;
    @Column(nullable = false, name = "created_at")
    protected LocalDateTime createdAt;
    @Column(nullable = false, name = "updated_at")
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onPersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}