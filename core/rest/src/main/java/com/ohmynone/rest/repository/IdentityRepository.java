package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, Long> {
    Optional<Identity> findByUid(UUID uuid);
}