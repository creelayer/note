package com.ohmynone.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_id", insertable = false, updatable = false)
    private long bookId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Book book;

    @Basic(optional = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @JsonIgnore
    @Getter
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default null")
    protected LocalDateTime deletedAt;
}
