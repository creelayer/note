package com.ohmynone.rest.pkg.user.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails  {

    @Id
    @GeneratedValue
    public UUID uuid;

    @Column(length = 100)
    public String username;

    @Column(length = 100)
    public String password;

    @Getter
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now() not null", updatable = false)
    protected LocalDateTime createdAt;

    @Getter
    @UpdateTimestamp
    @Column(columnDefinition = "timestamp default now() not null")
    protected LocalDateTime updatedAt;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
