package com.ohmynone.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ohmynone.user.util.TokenUtil;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Token {

    public static final int TOKEN_LENGTH = 57;
    public static final int TOKEN_ACCESS_LIFETIME_SEC = 60 * 60;
    public static final int TOKEN_REFRESH_LIFETIME_SEC = 60 * 60 * 24 * 7;

    @Id
    @GeneratedValue
    public long id;

    @JsonProperty("access_token")
    @Column(length = 100)
    public String access;

    @JsonProperty("refresh_token")
    @NotBlank(message = "refresh is mandatory")
    @Column(length = 100)
    public String refresh;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    public User user;

    @Getter
    @CreationTimestamp
    @Column(columnDefinition = "timestamp default now() not null", updatable = false)
    protected LocalDateTime createdAt;

    public Token() {
    }

    public Token(User user) {
        this.user = user;
        this.access = TokenUtil.random(TOKEN_LENGTH);
        this.refresh = TokenUtil.random(TOKEN_LENGTH);
    }

    public boolean isExpired() {
        return this.createdAt == null ||
                this.createdAt.plusSeconds(Token.TOKEN_ACCESS_LIFETIME_SEC).isBefore(LocalDateTime.now());
    }

    public boolean isRefreshExpired() {
        return this.createdAt == null ||
                this.createdAt.plusSeconds(Token.TOKEN_REFRESH_LIFETIME_SEC).isBefore(LocalDateTime.now());
    }
}
