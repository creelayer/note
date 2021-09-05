package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.repository.IdentityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class IdentityService implements UserDetailsService {

    protected IdentityRepository userRepository;

    public IdentityService(IdentityRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        return userRepository
                .findByUid(UUID.fromString(uid))
                .orElseThrow(() -> new UsernameNotFoundException(uid));
    }

    public UserDetails findOrCreate(Identity identity) {
        return userRepository
                .findByUid(identity.getUid())
                .orElseGet(() -> userRepository.save(identity));
    }
}
