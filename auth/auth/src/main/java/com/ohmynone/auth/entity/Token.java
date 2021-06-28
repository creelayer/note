package com.ohmynone.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Token extends BaseEntity {

    @Id
    private UUID uid;

    @Basic(optional = false)
    private String access;

    @Basic(optional = false)
    private String refresh;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
