package com.simplemoney.balance.Dto;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

public class BaseDto {

    @UpdateTimestamp
    @Column(name = "updated_at")
    Instant updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    Instant createdAt;
}
