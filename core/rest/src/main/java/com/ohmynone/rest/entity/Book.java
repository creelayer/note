package com.ohmynone.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Identity identity;

    @Basic
    private String name;

    @JsonIgnore
    @Getter
    @Column(columnDefinition = "timestamp default null")
    protected LocalDateTime deletedAt;
}
