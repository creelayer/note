package com.ohmynone.rest.service;

import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.entity.User;
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

    public Set<Tag> findAllByUser(User user) {
        return tagRepository.findAllByUserIdOrderByIdDesc(user.getId());
    }

    public Tag save(Tag book) {
        return tagRepository.save(book);
    }

    public Set<Tag> addTags(User user, Set<String> names) {

        Set<Tag> tags = tagRepository.findAllByUserIdAndNameIn(user.getId(), names);

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
