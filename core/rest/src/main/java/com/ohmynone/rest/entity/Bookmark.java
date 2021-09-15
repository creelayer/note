package com.ohmynone.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Book book;

    @Basic(optional = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private boolean pined = false;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "bookmark", fetch = FetchType.EAGER)
    private BookmarkSearchData searchData;

    @JsonIgnore
    @Getter
    @Column(columnDefinition = "timestamp default null")
    protected LocalDateTime deletedAt;
}
