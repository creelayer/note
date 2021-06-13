package com.ohmynone.rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic(optional = false)
    private String username;

    @Basic(optional = false)
    private String password;

    @Basic
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
