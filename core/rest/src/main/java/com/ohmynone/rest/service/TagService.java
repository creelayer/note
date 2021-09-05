package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TagService {

    protected TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Optional<Tag> findOne(Long id) {
        return tagRepository.findById(id);
    }

    public Set<Tag> findAllByIdentity(Identity identity) {
        return tagRepository.findAllByIdentityUidOrderByIdDesc(identity.getUid());
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Set<Tag> addTags(Identity user, Set<String> names) {

        Set<Tag> tags = tagRepository.findAllByIdentityUidAndNameIn(user.getUid(), names);

        for (Tag tag : tags) {
            names.remove(tag.getName());
        }

        for (String name : names) {
            Tag tag = new Tag(user, name);
            tags.add(save(tag));
        }

        return tags;
    }



}
