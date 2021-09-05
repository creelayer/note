package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    LinkedHashSet<Tag> findAllByIdentityUidOrderByIdDesc(UUID uid);
    Set<Tag> findAllByIdentityUidAndNameIn(UUID uid, Set<String> names);
}