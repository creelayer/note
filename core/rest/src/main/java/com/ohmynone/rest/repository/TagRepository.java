package com.ohmynone.rest.repository;

import com.ohmynone.rest.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Set<Tag> findAllByUserId(Long id);
    Set<Tag> findAllByUserIdAndNameIn(Long id, Set<String> names);
}