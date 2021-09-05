package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.TagDTO;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.mapper.TagMapper;
import com.ohmynone.rest.service.TagService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("v1/tag")
public class TagController {

    private final TagService tagService;
    private final TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper) {
        this.tagService = tagService;
        this.mapper = mapper;
    }

    @GetMapping("")
    Response<Set<TagDTO>> index(@AuthenticationPrincipal Identity identity, Response<Set<TagDTO>> model) {
        return model.setData(mapper.map(tagService.findAllByIdentity(identity)));
    }

    @PostMapping("/{id}")
    Response<TagDTO> updateBook(@PathVariable Long id,
                                 @Valid @RequestBody TagDTO dto,
                                 @AuthenticationPrincipal UserDetails userDetails,
                                 Response<TagDTO> model) {
        Tag tag = mapper.map(dto, tagService.findOne(id).orElseThrow());
        return model.setData(mapper.map(tagService.save(tag)));
    }
}
