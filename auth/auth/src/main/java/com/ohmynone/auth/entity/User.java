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
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID uid;

    @Basic(optional = false)
    private String username;

    @JsonIgnore
    @Basic(optional = false)
    private String password;

    @Basic
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
