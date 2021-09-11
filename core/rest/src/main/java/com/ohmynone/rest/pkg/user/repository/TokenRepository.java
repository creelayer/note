package com.ohmynone.rest.pkg.user.repository;

import com.ohmynone.rest.pkg.user.entity.Token;
import com.ohmynone.rest.pkg.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByAccess(String access);
    Optional<Token> findOneByRefresh(String refresh);
}
