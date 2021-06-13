package com.ohmynone.rest.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Getter
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now() not null", updatable = false)
    protected LocalDateTime createdAt;

    @Getter
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now() not null")
    protected LocalDateTime updatedAt;
}
