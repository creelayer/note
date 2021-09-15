package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.mapper.TagMapper;
import com.ohmynone.rest.service.TagService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    Response<List<TagDto>> index(@AuthenticationPrincipal Identity identity, Response<List<TagDto>> model) {
        return model.setData(mapper.map(tagService.findAllByIdentity(identity)));
    }

    @PostMapping("/{id}")
    @PreAuthorize("#tag.identity.username == principal.username")
    Response<TagDto> updateTag(@PathVariable("id") Tag tag,
                                @Valid @RequestBody TagDto dto,
                                Response<TagDto> model) {
        tag = mapper.map(dto, tag);
        return model.setData(mapper.map(tagService.save(tag)));
    }
}
