package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByIdentityUidOrderByIdDesc(UUID uid);
    Set<Tag> findAllByIdentityUidAndNameIn(UUID uid, Set<String> names);
}