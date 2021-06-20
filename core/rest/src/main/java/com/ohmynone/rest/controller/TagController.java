package com.ohmynone.rest.controller;

import com.ohmynone.rest.component.CurrentUserDetails;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.TagDTO;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.mapper.TagMapper;
import com.ohmynone.rest.service.TagService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    Response<Set<TagDTO>> index(@AuthenticationPrincipal CurrentUserDetails userDetails, Response<Set<TagDTO>> model) {
        return model.setData(mapper.map(tagService.findAllByUser(userDetails.getUser())));
    }

    @PostMapping("/{id}")
    Response<TagDTO> updateBook(@PathVariable Long id,
                                 @Valid @RequestBody TagDTO dto,
                                 @AuthenticationPrincipal CurrentUserDetails userDetails,
                                 Response<TagDTO> model) {
        Tag tag = mapper.map(dto, tagService.findOne(id).orElseThrow());
        return model.setData(mapper.map(tagService.save(tag)));
    }
}
