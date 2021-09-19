package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.repository.IdentityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class IdentityService {

    protected IdentityRepository identityRepository;

    public IdentityService(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    public Identity findOrCreate(UUID uuid) {
        return identityRepository
                .findByUid(uuid)
                .orElseGet(() -> {
                    try {
                        return identityRepository.save(new Identity(uuid));
                    } catch (DataIntegrityViolationException e) {
                        return identityRepository.findByUid(uuid)
                                .orElseThrow(() -> new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage())));
                    }
                });
    }
}
